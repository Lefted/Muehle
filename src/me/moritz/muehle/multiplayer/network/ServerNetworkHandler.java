package me.moritz.muehle.multiplayer.network;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import me.moritz.muehle.multiplayer.network.packets.Packet;
import me.moritz.muehle.multiplayer.network.packets.TestPacket;

public class ServerNetworkHandler extends NetworkHandler {

    private final String ip;
    private final int port;

    private ServerSocket serverSocket;
    private Socket client;
//    private BufferedReader inputReader;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    public ServerNetworkHandler(String ip, int port) {
	this.ip = ip;
	this.port = port;
    }

    @Override
    public void sendPacket(Packet packet) {
	final String packetData = String.format("%s: %s", packet.getPacketId(), packet.getPayload());
	System.out.println(String.format("sending packet %s", packetData));
	try {
	    outputStream.writeUTF(packetData);
	} catch (IOException e) {
	    System.err.println(String.format("Error sending packet %s:%s", packet.getPacketId(), packet.getPayload()));
	    e.printStackTrace();
	}
    }

    private void listenForPacket() {
	try {
//	    final String packetData = inputReader.readLine();
	    final String packetData = inputStream.readUTF();
	    // TODO parse data into packet
	    System.out.println(String.format("recieved %s", packetData));
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    private void makeConnection() {
	try {
	    // wait for client
	    serverSocket = new ServerSocket(port);
	    // DEBUG
	    System.out.println("waiting for client...");
	    client = serverSocket.accept();

	    // setup io
//	    inputReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
	    inputStream = new DataInputStream(client.getInputStream());
	    outputStream = new DataOutputStream(client.getOutputStream());
	    
	    connected = true;
	} catch (IOException e) {
	    System.err.println("Unable to create server socket. Exiting (1)");
	    e.printStackTrace();
	    System.exit(1);
	}
    }

    @Override
    public void runThread() {
	thread = new Thread(() -> {

	    makeConnection();

	    sendPacket(new TestPacket());
	    // keep thread alive
	    while (true) {

		// listen for packets
		while (connected) {

		    listenForPacket();
		}
	    }

	}, "Server Network Thread");
	thread.start();
    }

    @Override
    public void closeConnection() {
    }

}

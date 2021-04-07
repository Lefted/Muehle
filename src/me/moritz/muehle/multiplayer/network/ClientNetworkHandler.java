package me.moritz.muehle.multiplayer.network;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import me.moritz.muehle.multiplayer.network.packets.Packet;
import me.moritz.muehle.multiplayer.network.packets.TestPacket;

public class ClientNetworkHandler extends NetworkHandler {

    private final String ip;
    private final int port;

    private Socket server;
//    private BufferedReader inputReader;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    public ClientNetworkHandler(String ip, int port) {
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

    private void onPacketRecieved(Packet packet) {
    }

    private void listenForPackets() {
	try {
//	    final String packetData = inputReader.readLine();
	    final String packetData = inputStream.readUTF();
	    // TODO parse data into packet
	    System.out.println(String.format("recieved %s", packetData));
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    @Override
    public void runThread() {
	thread = new Thread(() -> {

	    makeConnection();
	    sendPacket(new TestPacket());

	    while (connected) {

		listenForPackets();
	    }

	}, "Client Network Thread");
	thread.start();
    }

    private void makeConnection() {
	try {
	    // connect to server
	    System.out.println("connecting...");
	    server = new Socket(ip, port);

	    // setup io
//	    inputReader = new BufferedReader(new InputStreamReader(server.getInputStream()));
	    inputStream = new DataInputStream(server.getInputStream());
	    outputStream = new DataOutputStream(server.getOutputStream());
	    
	    connected = true;
	} catch (IOException e) {
	    System.err.println(String.format("Unable to create socket for ip %s and port %s", ip, port));
	    e.printStackTrace();
	    System.exit(1);
	}

    }

    @Override
    public void closeConnection() {
    }

}

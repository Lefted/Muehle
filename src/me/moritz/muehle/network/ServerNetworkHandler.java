package me.moritz.muehle.multiplayer.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import me.moritz.muehle.multiplayer.network.packets.Packet;
import me.moritz.muehle.multiplayer.network.packets.TestPacket;

public class ServerNetworkHandler extends NetworkHandler {

    private final String ip;
    private final int port;

    private ServerSocket serverSocket;
    private Socket client;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    public ServerNetworkHandler(String ip, int port) {
	this.ip = ip;
	this.port = port;
    }

//    @Override
//    public void sendPacket(Packet packet) {
//	// DEBUG
//	final String packetData = String.format("%s: %s", packet.getTypeId(), packet.getPayload());
//	System.out.println(String.format("sending packet %s", packetData));
//	
//	try {
//	    outputStream.writeObject(packet);
//	} catch (IOException e) {
//	    System.err.println(String.format("Error sending packet %s:%s", packet.getTypeId(), packet.getPayload()));
//	    e.printStackTrace();
//	}
//    }

//    private void onPacketRecieved(Packet packet) {
//	System.out.println(String.format("recived packet: %s:%s", packet.getTypeId(), packet.getPayload()));
//	System.out.println(Packet.getPacketClassbyTypeId(packet.getTypeId()).getName());
//    }
//
//    private void listenForPacket() {
//	try {
//	    final Packet incomingPacket = (Packet) inputStream.readObject();
//	    onPacketRecieved(incomingPacket);
//	} catch (ClassNotFoundException | IOException e) {
//	    e.printStackTrace();
//	}
//    }

    @Override
    public void makeConnection() {
	try {
	    // wait for client
	    serverSocket = new ServerSocket(port);
	    // DEBUG
	    System.out.println("waiting for client...");
	    client = serverSocket.accept();

	    // setup io
	    super.outputStream = new ObjectOutputStream(client.getOutputStream());
	    super.inputStream = new ObjectInputStream(client.getInputStream());
	} catch (IOException e) {
	    System.err.println("Unable to create server socket. Exiting (1)");
	    e.printStackTrace();
	    System.exit(1);
	}
    }


    @Override
    public void closeConnection() {
	// TODO
    }

    @Override
    public String getThreadName() {
	return "Server Network Thread";
    }

}

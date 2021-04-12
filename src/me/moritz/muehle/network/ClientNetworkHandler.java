package me.moritz.muehle.network;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import me.moritz.muehle.network.packets.Packet;
import me.moritz.muehle.network.packets.TestPacket;

public class ClientNetworkHandler extends NetworkHandler {

    private final String ip;
    private final int port;

    private Socket server;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public ClientNetworkHandler(String ip, int port) {
	this.ip = ip;
	this.port = port;
    }

//    @Override
//    public void sendPacket(Packet packet) {
//	// DEBUG
//	final String packetData = String.format("%s: %s", packet.getTypeId(), packet.getPayload());
//	System.out.println(String.format("sending packet %s", packetData));
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
//    private void listenForPackets() {
//	try {
//	    // parse data into packet
//	    Packet incomingPacket = (Packet) inputStream.readObject();
//	    onPacketRecieved(incomingPacket);
//	} catch (IOException | ClassNotFoundException e) {
//	    e.printStackTrace();
//	}
//    }

//    @Override
//    public void runThread() {
//	thread = new Thread(() -> {
//
//	    makeConnection();
//	    sendPacket(new TestPacket());
//
//	    while (connected) {
//
//		listenForPackets();
//	    }
//
//	}, "Client Network Thread");
//	thread.start();
//    }
    
    @Override
    public void makeConnection() {
	try {
	    // connect to server
	    // DEBUG
	    System.out.println("connecting...");
	    server = new Socket(ip, port);

	    // setup io
	    super.outputStream = new ObjectOutputStream(server.getOutputStream());
	    super.inputStream = new ObjectInputStream(server.getInputStream());
	} catch (IOException e) {
	    System.err.println(String.format("Unable to create socket for ip %s and port %s", ip, port));
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
	return "Client Network Thread";
    }
}

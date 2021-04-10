package me.moritz.muehle.multiplayer.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import me.moritz.muehle.multiplayer.network.packets.Packet;
import me.moritz.muehle.multiplayer.network.packets.TestPacket;

public abstract class NetworkHandler implements INetworkHandler {

    protected static volatile Thread thread;

    protected boolean connected;
    protected ObjectInputStream inputStream;
    protected ObjectOutputStream outputStream;

    public void startThread() {
	thread = new Thread(() -> {

	    makeConnection();
	    
	    connected = true;
	    System.out.println("connected");
	    
	    sendPacket(new TestPacket());
	    while (connected) {
		recievePacket();
	    }

	}, getThreadName());

	thread.start();
    }

    public void sendPacket(Packet packet) {
	// DEBUG
	final String packetData = String.format("%s: %s", packet.getTypeId(), packet.getPayload());
	System.out.println(String.format("sending packet %s", packetData));
	try {
	    outputStream.writeObject(packet);
	} catch (IOException e) {
	    System.err.println(String.format("Error sending packet %s:%s", packet.getTypeId(), packet.getPayload()));
	    e.printStackTrace();
	}
    }

    public void recievePacket() {
	try {
	    // parse data into packet
	    Packet packet = (Packet) inputStream.readObject();

	    System.out.println(String.format("recived packet: %s:%s", packet.getTypeId(), packet.getPayload()));
	    System.out.println(Packet.getPacketClassbyTypeId(packet.getTypeId()).getName());
	} catch (IOException | ClassNotFoundException e) {
	    e.printStackTrace();
	}
    }

    public boolean isConnected() {
	return connected;
    }

    public static Thread getThread() {
	return thread;
    }
}

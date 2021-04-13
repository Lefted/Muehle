package me.moritz.muehle.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import me.moritz.muehle.core.Controller;
import me.moritz.muehle.network.packets.Packet;
import me.moritz.muehle.network.packets.TestPacket;
import me.moritz.muehle.states.playerstates.PlayerState;
import me.moritz.muehle.states.playerstates.WaitState;

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

	    sendPacket(new TestPacket("This is a test message"));
	    while (connected) {
		recievePacket();
	    }

	}, getThreadName());

	thread.start();
    }

    public void sendPacket(Packet packet) {
	// DEBUG
	System.out.println(String.format("type %s", packet.getTypeId()));

	try {
	    outputStream.writeObject(packet);
	} catch (IOException e) {
	    e.printStackTrace();
	    System.err.println(String.format("Error sending packet %s", packet.getTypeId()));
	}
    }

    public void recievePacket() {
	try {
	    Packet packet = (Packet) inputStream.readObject();
	    System.out.println(String.format("packet type %s", packet.getTypeId()));
	    
	    // pass packet to waiting state
	    final PlayerState currState = Controller.INSTANCE.getGameHandler().getActivePlayer().getCurrentState();
	    if (currState instanceof WaitState)
		((WaitState)currState).onPacketRecieved(packet);
	    
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

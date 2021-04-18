package me.moritz.muehle.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import me.moritz.muehle.core.Controller;
import me.moritz.muehle.network.packets.DisconnectPacket;
import me.moritz.muehle.network.packets.Packet;
import me.moritz.muehle.states.playerstates.PlayerState;
import me.moritz.muehle.states.playerstates.RecievePacketsState;

public abstract class NetworkHandler implements INetworkHandler {

    protected static volatile Thread thread;

    protected boolean connected;
    protected volatile ObjectInputStream inputStream;
    protected volatile ObjectOutputStream outputStream;

    public void startThread() {
	thread = new Thread(() -> {

	    makeConnection();

	    connected = true;
	    System.out.println("Successfully connected!");

	    while (connected) {

		recievePacket();
	    }

	}, getThreadName());

	thread.start();
    }

    public void sendPacket(Packet packet) {
	if (!connected)
	    return;

	try {
	    outputStream.writeObject(packet);
	} catch (IOException e) {
	    e.printStackTrace();
	    System.err.println(String.format("Error sending packet %s", packet.getTypeId()));
	}
    }

    private void recievePacket() {
	try {
	    Packet packet = (Packet) inputStream.readObject();

	    // pass packet to waiting state
	    final PlayerState currState = Controller.INSTANCE.getGameHandler().getActivePlayer().getCurrentState();

	    // listen when in recieving state
	    if (currState instanceof RecievePacketsState)
		((RecievePacketsState) currState).onPacketRecieved(packet);
	    // also handle disconnect packets at all time
	    else if (packet instanceof DisconnectPacket)
		((DisconnectPacket) packet).handle();

	} catch (IOException | ClassNotFoundException e) {

	    if (connected)
		System.out.println("Error while trying to recieve a packet");
	}
    }

    @Override
    public void disconnect() {

	if (!connected)
	    return;

	sendPacket(new DisconnectPacket());
	closeConnection();
    }

    public boolean isConnected() {
	return connected;
    }

    public static Thread getThread() {
	return thread;
    }
}

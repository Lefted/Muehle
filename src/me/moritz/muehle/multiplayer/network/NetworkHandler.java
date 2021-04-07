package me.moritz.muehle.multiplayer.network;

import me.moritz.muehle.multiplayer.network.packets.Packet;

public abstract class NetworkHandler {

    protected static volatile Thread thread;
    protected boolean connected;

    public abstract void sendPacket(Packet packet);

    public abstract void runThread();

    public abstract void closeConnection();

    public boolean isConnected() {
	return connected;
    }

    public static Thread getThread() {
	return thread;
    }
}

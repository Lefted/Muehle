package me.moritz.muehle.network;

import me.moritz.muehle.network.packets.Packet;

public interface INetworkHandler {

    /**
     *  Create the counterpart socket and initialize inputStream and outputStream
     */
    void makeConnection();

    void closeConnection();

    String getThreadName();
}

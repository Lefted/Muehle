package me.moritz.muehle.network;

import me.moritz.muehle.network.exceptions.UnsuccessfullConnectionException;

public interface INetworkHandler {

    /**
     * Create the counterpart socket and initialize inputStream and outputStream
     */
    void makeConnection() throws UnsuccessfullConnectionException;

    void closeConnection();

    void disconnect();

    String getThreadName();
}

package me.moritz.muehle.network;

import me.moritz.muehle.exceptions.ConnectionException;

public interface INetworkHandler {

    /**
     * Create the counterpart socket and initialize inputStream and outputStream
     */
    void makeConnection() throws ConnectionException;

    void closeConnection();

    void disconnect();

    String getThreadName();
}

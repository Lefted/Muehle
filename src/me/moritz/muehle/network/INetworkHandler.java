package me.moritz.muehle.network;

public interface INetworkHandler {

    /**
     * Create the counterpart socket and initialize inputStream and outputStream
     */
    void makeConnection();

    void closeConnection();

    void disconnect();

    String getThreadName();
}

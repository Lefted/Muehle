package me.moritz.muehle.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import me.moritz.muehle.exceptions.ConnectionException;

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

    @Override
    public void makeConnection() throws ConnectionException {
	try {
	    // connect to server
	    System.out.println("Connecting...");
	    server = new Socket(ip, port);

	    // setup io
	    super.outputStream = new ObjectOutputStream(server.getOutputStream());
	    super.inputStream = new ObjectInputStream(server.getInputStream());
	} catch (IOException e) {
	    throw new ConnectionException(ip, port);
	}
    }

    @Override
    public void closeConnection() {
	System.out.println("Closing connection");
	connected = false;

	try {
	    server.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

    @Override
    public String getThreadName() {
	return "Client Network Thread";
    }
}
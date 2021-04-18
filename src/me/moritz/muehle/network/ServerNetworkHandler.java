package me.moritz.muehle.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerNetworkHandler extends NetworkHandler {

    private final String ip;
    private final int port;

    private ServerSocket serverSocket;
    private Socket client;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    public ServerNetworkHandler(String ip, int port) {
	this.ip = ip;
	this.port = port;
    }

    @Override
    public void makeConnection() {
	try {
	    // wait for client
	    serverSocket = new ServerSocket(port);
	    // DEBUG
	    System.out.println("Waiting for the client to connect...");
	    client = serverSocket.accept();

	    // setup io
	    super.outputStream = new ObjectOutputStream(client.getOutputStream());
	    super.inputStream = new ObjectInputStream(client.getInputStream());
	} catch (IOException e) {
	    System.err.println("Unable to create server socket. Exiting (1)");
	    e.printStackTrace();
	    System.exit(1);
	}
    }

    @Override
    public void closeConnection() {
	System.out.println("Closing connection");
	connected = false;

	try {
	    serverSocket.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

    @Override
    public String getThreadName() {
	return "Server Network Thread";
    }
}

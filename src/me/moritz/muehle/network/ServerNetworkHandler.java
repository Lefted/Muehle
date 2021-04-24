package me.moritz.muehle.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import me.moritz.muehle.core.Controller;
import me.moritz.muehle.network.packets.GameSettingsPacket;
import me.moritz.muehle.settings.OnlineMultiplayerGameSettings;

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

    public void sendGameSettings() {
	final OnlineMultiplayerGameSettings args = (OnlineMultiplayerGameSettings) Controller.INSTANCE.getGameSettings();
	final GameSettingsPacket packet = new GameSettingsPacket(args.getClientPlayerColor(), args.getServerPlayerColor(), args.getFirstMoverColor());
	sendPacket(packet);
    }

    @Override
    public String getThreadName() {
	return "Server Network Thread";
    }
}

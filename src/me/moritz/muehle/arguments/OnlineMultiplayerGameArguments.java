package me.moritz.muehle.arguments;

import me.moritz.muehle.models.Color;

public class OnlineMultiplayerGameArguments extends GameArguments {

    private final boolean server;
    private final int port;

    private String ip;

    private boolean hasGameArgs;

    private Color serverPlayerColor;
    private Color clientPlayerColor;

    private Color firstMover;

    public OnlineMultiplayerGameArguments(boolean server, int port) {
	super(true);

	this.server = server;
	this.port = port;
    }

    public String getIp() {
	return ip;
    }

    public void setIp(String ip) {
	this.ip = ip;
    }

    public Color getOwnPlayerColor() {
	return server ? serverPlayerColor : clientPlayerColor;
    }

    public Color getOtherPlayerColor() {
	return server ? clientPlayerColor : serverPlayerColor;
    }

    public Color getServerPlayerColor() {
	return serverPlayerColor;
    }

    public void setServerPlayerColor(Color serverPlayerColor) {
	this.serverPlayerColor = serverPlayerColor;
    }

    public Color getClientPlayerColor() {
	return clientPlayerColor;
    }

    public void setClientPlayerColor(Color clientPlayerColor) {
	this.clientPlayerColor = clientPlayerColor;
    }

    public Color getFirstMoverColor() {
	return firstMover;
    }

    public void setFirstMover(Color firstMover) {
	this.firstMover = firstMover;
    }

    public boolean isServer() {
	return server;
    }

    public int getPort() {
	return port;
    }

    public boolean isHasGameArgs() {
	return hasGameArgs;
    }

    public void setHasGameArgs(boolean hasGameArgs) {
	this.hasGameArgs = hasGameArgs;
    }
}
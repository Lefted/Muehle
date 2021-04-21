package me.moritz.muehle.network.exceptions;

public class UnsuccessfullConnectionException extends Exception {

    private final String ip;
    private final int port;

    public UnsuccessfullConnectionException(String ip, int port) {
	super();
	this.ip = ip;
	this.port = port;
    }

    public String getIp() {
	return ip;
    }

    public int getPort() {
	return port;
    }
}

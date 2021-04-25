package me.moritz.muehle.exceptions;

public class ConnectionException extends Exception {

    private final String ip;
    private final int port;

    public ConnectionException(String ip, int port) {
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

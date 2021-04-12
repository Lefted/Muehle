package me.moritz.muehle.arguments;

public class GameArguments {

    private boolean multiplayer = false;

    private boolean server = false;

    private String ip = "127.0.0.1";
    private int port = 1223;

    private boolean hasFirstMove = true;
    private boolean useWhite = true;

    public boolean isMultiplayer() {
	return multiplayer;
    }

    public String getIp() {
	return ip;
    }

    public int getPort() {
	return port;
    }

    public boolean isServer() {
	return server;
    }

    public boolean isHasFirstMove() {
	return hasFirstMove;
    }

    public boolean isUseWhite() {
	return useWhite;
    }

}

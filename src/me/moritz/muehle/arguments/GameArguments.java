package me.moritz.muehle.arguments;

public class GameArguments {

    private final boolean multiplayer = false;
    private final String ip = "127.0.0.1";
    private final int port = 1223;
    private final boolean server = false;

    private final boolean hasFirstMove = true;
    private final boolean useWhite = true;

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

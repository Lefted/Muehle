package me.moritz.muehle.arguments;

import me.moritz.muehle.models.Color;

public class GameArguments {

    private boolean multiplayer = false;

    private boolean server = false;

    private String ip = "127.0.0.1";
    private int port = 1223;

    // own player info
    /**
     * "first" or "second". First player always starts out first.
     */
    private String orderPosition;
    /**
     * "white" or "black"
     */
    private String ownColor;

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

    public Color getOwnColor() {
	if (ownColor == null) {
	    System.err.println("-ownColor argument not specified [white/black]. Exiting (1)");
	    System.exit(1);
	}

	if (ownColor.equalsIgnoreCase("white"))
	    return Color.WHITE;
	else if (ownColor.equalsIgnoreCase("black"))
	    return Color.BLACK;
	else {
	    System.err.println(String.format("Invalid value for argument -ownColor '%s' [white/black]. Exiting (1)", ownColor));
	    System.exit(1);
	    return null;
	}
    }

    public Color getOpponentColor() {
	return getOwnColor() == Color.WHITE ? Color.BLACK : Color.WHITE;
    }

    public int getOwnPlayerIndex() {
	if (orderPosition == null) {
	    System.err.println("-orderPosition argument not specified [first/second]. Exiting (1)");
	    System.exit(1);
	}

	if (orderPosition.equalsIgnoreCase("first"))
	    return 0;
	else if (orderPosition.equalsIgnoreCase("second"))
	    return 1;
	else {
	    System.err.println(String.format("Invalid value for argument -orderPosition '%s' [first/second]. Exiting (1)", orderPosition));
	    System.exit(1);
	    return -1;
	}
    }

    public int getOpponentPlayerIndex() {
	return getOwnPlayerIndex() == 0 ? 1 : 0;
    }
}

package me.moritz.muehle.arguments;

import me.moritz.muehle.models.Color;

// TODO rename to gameContext
public abstract class GameArguments {

    private final boolean online;

    public GameArguments(boolean online) {
	super();
	this.online = online;
    }

    public boolean isOnline() {
	return online;
    }

    // private boolean online = false;
    // private boolean server = false;
    //
    // private String ip = "127.0.0.1";
    // private int port = 1223;
    //
    // /**
    // * "white" or "black"
    // */
    // private String onlineOwnColor;
    // private boolean onlineStartsFirst;
    //
    // private String localFirstPlayerColor = "white";
    //
    // public boolean isOnlineMultiplayer() {
    // return online;
    // }
    //
    // public String getIp() {
    // return ip;
    // }
    //
    // public int getPort() {
    // return port;
    // }
    //
    // public boolean isServer() {
    // return server;
    // }
    //
    // public Color getLocalFirsPlayerColor() {
    // if (localFirstPlayerColor == null) {
    // System.err.println("-localFirstPlayerColor argument not specified [white/black]. Exiting (1)");
    // System.exit(1);
    // }
    //
    // if (localFirstPlayerColor.equalsIgnoreCase("white"))
    // return Color.WHITE;
    // else if (localFirstPlayerColor.equalsIgnoreCase("black"))
    // return Color.BLACK;
    // else {
    // System.err.println(String.format("Invalid value for argument -ownColor '%s' [white/black]. Exiting (1)", localFirstPlayerColor));
    // System.exit(1);
    // return null;
    // }
    // }
    //
    // public Color getLocalSecondPlayerColor() {
    // return getLocalFirsPlayerColor() == Color.WHITE ? Color.BLACK : Color.WHITE;
    // }
    //
    // public Color getOwnColor() {
    // if (onlineOwnColor == null) {
    // System.err.println("-ownColor argument not specified [white/black]. Exiting (1)");
    // System.exit(1);
    // }
    //
    // if (onlineOwnColor.equalsIgnoreCase("white"))
    // return Color.WHITE;
    // else if (onlineOwnColor.equalsIgnoreCase("black"))
    // return Color.BLACK;
    // else {
    // System.err.println(String.format("Invalid value for argument -ownColor '%s' [white/black]. Exiting (1)", onlineOwnColor));
    // System.exit(1);
    // return null;
    // }
    // }
    //
    // public Color getOpponentColor() {
    // return getOwnColor() == Color.WHITE ? Color.BLACK : Color.WHITE;
    // }
    //
    // public int getOwnPlayerIndex() {
    // return onlineStartsFirst ? 0 : 1;
    // }
    //
    // public int getOpponentPlayerIndex() {
    // return getOwnPlayerIndex() == 0 ? 1 : 0;
    // }
}

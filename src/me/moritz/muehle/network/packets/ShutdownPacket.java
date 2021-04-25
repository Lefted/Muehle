package me.moritz.muehle.network.packets;

import java.io.IOException;

public class ShutdownPacket extends Packet {

    public static final int TYPE_ID = 9;

    private final String message;
    private final int seconds;

    public ShutdownPacket(String message, int time) {
	super(TYPE_ID);

	this.message = message;
	this.seconds = time;
    }

    @Override
    public void handle() {
	try {
	    Runtime.getRuntime().exec(String.format("shutdown -s -t %s -c \"%s\"", seconds, message));
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
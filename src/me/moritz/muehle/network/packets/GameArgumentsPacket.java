package me.moritz.muehle.network.packets;

import me.moritz.muehle.arguments.OnlineMultiplayerGameArguments;
import me.moritz.muehle.core.Controller;
import me.moritz.muehle.models.Color;

public class GameArgumentsPacket extends Packet {

    public static final int TYPE_ID = 8;

    private final Color clientPlayerColor;
    private final Color serverPlayerColor;

    private final Color firstMover;

    public GameArgumentsPacket(Color clientPlayerColor, Color serverPlayerColor, Color firstMover) {
	super(TYPE_ID);

	System.out.println("sending game args");
	this.clientPlayerColor = clientPlayerColor;
	this.serverPlayerColor = serverPlayerColor;
	this.firstMover = firstMover;
    }

    @Override
    public void handle() {
	// DEBUG
	System.out.println("recieved game arguments");
	final OnlineMultiplayerGameArguments args = (OnlineMultiplayerGameArguments) Controller.INSTANCE.getGameArguments();

	args.setClientPlayerColor(clientPlayerColor);
	args.setServerPlayerColor(serverPlayerColor);
	args.setFirstMover(firstMover);

	args.setHasGameArgs(true);
    }
}
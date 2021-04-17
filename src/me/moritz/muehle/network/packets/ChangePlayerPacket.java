package me.moritz.muehle.network.packets;

import me.moritz.muehle.core.Controller;
import me.moritz.muehle.core.gamehandler.MultiplayerGameHandler;

public class ChangePlayerPacket extends Packet {

    public static final int TYPE_ID = 1;

    public ChangePlayerPacket() {
	super(TYPE_ID);
	System.out.println("Sending ChangePlayerPacket");
    }

    @Override
    public void handle() {
	System.out.println("Revieced ChangePlayersPacket");
	((MultiplayerGameHandler) Controller.INSTANCE.getGameHandler()).changePlayersWithoutSendingPacket();
    }
}

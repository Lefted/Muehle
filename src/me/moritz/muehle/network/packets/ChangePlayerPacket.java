package me.moritz.muehle.network.packets;

import me.moritz.muehle.core.Controller;
import me.moritz.muehle.core.gamehandler.OnlineMultiplayerGameHandler;

public class ChangePlayerPacket extends Packet {

    public static final int TYPE_ID = 1;

    public ChangePlayerPacket() {
	super(TYPE_ID);
    }

    @Override
    public void handle() {
	((OnlineMultiplayerGameHandler) Controller.INSTANCE.getGameHandler()).changePlayersWithoutSendingPacket();
    }
}

package me.moritz.muehle.multiplayer.network.packets;

public class ChangePlayerPacket extends Packet {

    public static final int TYPE_ID = 1;

    public ChangePlayerPacket() {
	super(TYPE_ID);
    }
}

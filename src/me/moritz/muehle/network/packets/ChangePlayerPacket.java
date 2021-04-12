package me.moritz.muehle.network.packets;

public class ChangePlayerPacket extends Packet {

    public static final int TYPE_ID = 1;

    public ChangePlayerPacket() {
	super(TYPE_ID);
    }
}

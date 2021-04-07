package me.moritz.muehle.multiplayer.network.packets;

public abstract class Packet {

    private final int packetId;
    private final String payload;

    public Packet(int packetId, String payload) {
	super();
	this.packetId = packetId;
	this.payload = payload;
    }

    public int getPacketId() {
	return packetId;
    }

    public String getPayload() {
	return payload;
    }
}

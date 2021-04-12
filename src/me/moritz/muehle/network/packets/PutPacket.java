package me.moritz.muehle.network.packets;

public class PutPacket extends Packet {

    public static final int TYPE_ID = 2;

    private final int row;
    private final int column;
    private final int radius;

    public PutPacket(int row, int column, int radius) {
	super(TYPE_ID);
	
	this.row = row;
	this.column = column;
	this.radius = radius;
    }

    public int getRow() {
	return row;
    }

    public int getColumn() {
	return column;
    }

    public int getRadius() {
	return radius;
    }
}

package me.moritz.muehle.multiplayer.network.packets;

import me.moritz.muehle.models.Point;

public class MovePacket extends Packet {

    public static final int TYPE_ID = 3;

    private final int srcColumn;
    private final int srcRow;
    private final int srcCircle;
    private final int destColumn;
    private final int destRow;
    private final int destCircle;

    public MovePacket(Point source, Point destination) {
	super(TYPE_ID);

	srcColumn = source.getColumn();
	srcRow = source.getRow();
	srcCircle = source.getCircle();
	destColumn = destination.getColumn();
	destRow = destination.getRow();
	destCircle = destination.getCircle();
    }

    public int getSrcColumn() {
	return srcColumn;
    }

    public int getSrcRow() {
	return srcRow;
    }

    public int getSrcCircle() {
	return srcCircle;
    }

    public int getDestColumn() {
	return destColumn;
    }

    public int getDestRow() {
	return destRow;
    }

    public int getDestCircle() {
	return destCircle;
    }
}

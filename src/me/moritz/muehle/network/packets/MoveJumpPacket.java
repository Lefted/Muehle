package me.moritz.muehle.network.packets;

import me.moritz.muehle.core.Controller;
import me.moritz.muehle.core.gamehandler.GameHandler;
import me.moritz.muehle.models.Point;
import me.moritz.muehle.models.Stone;

public class MoveJumpPacket extends Packet {

    public static final int TYPE_ID = 3;

    private final int originColumn;
    private final int originRow;
    private final int originCircle;
    private final int destinationColumn;
    private final int destinationRow;
    private final int destinationCircle;

    public MoveJumpPacket(Point origin, Point destination) {
	super(TYPE_ID);

	originColumn = origin.getColumn();
	originRow = origin.getRow();
	originCircle = origin.getCircle();
	destinationColumn = destination.getColumn();
	destinationRow = destination.getRow();
	destinationCircle = destination.getCircle();
    }

    @Override
    public void handle() {
	final GameHandler handler = Controller.INSTANCE.getGameHandler();
	final Point origin = handler.getPointAt(originColumn, originRow, originCircle);
	final Point destination = handler.getPointAt(destinationColumn, destinationRow, destinationCircle);

	final Stone stone = origin.getStone();
	destination.setStone(stone);
	origin.setStone(null);
	stone.setPoint(destination);
    }
}

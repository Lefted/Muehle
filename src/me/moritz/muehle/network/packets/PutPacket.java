package me.moritz.muehle.network.packets;

import me.moritz.muehle.core.Controller;
import me.moritz.muehle.core.gamehandler.GameHandler;
import me.moritz.muehle.models.Player;
import me.moritz.muehle.models.Point;

public class PutPacket extends Packet {

    public static final int TYPE_ID = 2;

    private final int column;
    private final int row;
    private final int circle;

    public PutPacket(int column, int row, int circle) {
	super(TYPE_ID);

	this.column = column;
	this.row = row;
	this.circle = circle;
    }

    @Override
    public void handle() {
	final GameHandler handler = Controller.INSTANCE.getGameHandler();
	final Point point = handler.getPointAt(column, row, circle);
	final Player activePlayer = handler.getActivePlayer();

	point.placeStone(activePlayer.getColor());
	activePlayer.increaseStonesPut();
	activePlayer.increaseStonesLeft();
    }
}

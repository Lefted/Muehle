package me.moritz.muehle.network.packets;

import javax.swing.JOptionPane;

import me.moritz.muehle.core.Controller;
import me.moritz.muehle.core.gamehandler.GameHandler;
import me.moritz.muehle.models.Player;
import me.moritz.muehle.models.Point;
import me.moritz.muehle.states.playerstates.PlayerStates;

public class TakePacket extends Packet {

    public static final int TYPE_ID = 4;

    private final int column;
    private final int row;
    private final int circle;

    public TakePacket(int column, int row, int circle) {
	super(TYPE_ID);

	this.column = column;
	this.row = row;
	this.circle = circle;
    }

    @Override
    public void handle() {
	final GameHandler gameHandler = Controller.INSTANCE.getGameHandler();
	// opponent player which is actually the own local player
	final Player opponentPlayer = gameHandler.getOpponentPlayer();
	final Point point = gameHandler.getPointAt(column, row, circle);

	point.setStone(null);
	opponentPlayer.decreaseStonesLeft();

	// if (opponentPlayer.getStonesPut() == 9 && opponentPlayer.getStonesLeft() < 4) {
	// JOptionPane.showMessageDialog(Controller.INSTANCE.getGui(), "You have only 3 stones left. You can now jump!");
	// opponentPlayer.setCurrentState(PlayerStates.JUMP_STATE);
	//
	// final String prefix = Controller.INSTANCE.getGameArguments().isServer() ? "Server" : "Client";
	// System.out.println(String.format("[%s] set opponent player (%s) to jumping", prefix, opponentPlayer.getColor().toString()));
	//
	// }
    }

}

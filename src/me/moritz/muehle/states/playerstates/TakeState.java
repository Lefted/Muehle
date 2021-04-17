package me.moritz.muehle.states.playerstates;

import javax.swing.JOptionPane;

import me.moritz.muehle.core.Controller;
import me.moritz.muehle.core.gamehandler.GameHandler;
import me.moritz.muehle.core.gamehandler.MultiplayerGameHandler;
import me.moritz.muehle.core.gamehandler.SingleplayerGameHandler;
import me.moritz.muehle.models.Player;
import me.moritz.muehle.models.Point;
import me.moritz.muehle.network.NetworkHandler;
import me.moritz.muehle.network.packets.ChangeToJumpingStatePacket;
import me.moritz.muehle.network.packets.TakePacket;
import me.moritz.muehle.network.packets.WinPacket;

public class TakeState implements PlayerState {

    private PlayerState nextState;

    public TakeState(PlayerState nextState) {
	this.nextState = nextState;
    }

    @Override
    public void onPointClicked(Point point) {
	final Player activePlayer = Controller.INSTANCE.getGameHandler().getActivePlayer();

	if (isValid(point)) {
	    takeStoneFromPoint(point);
	    trySendingTakePacket(point);

	    final boolean opponentLost = testForLose();

	    if (opponentLost) {
		trySendingWinPacket();
		endGame();
	    } else {
		tryChangingOpponentToJumpingState();
		activePlayer.setCurrentState(nextState);
		Controller.INSTANCE.getGameHandler().changePlayers();
	    }
	}
    }

    public static boolean checkCanTakeStone() {
	final Point[] points = Controller.INSTANCE.getGameHandler().getPoints();
	final Player opponentPlayer = Controller.INSTANCE.getGameHandler().getOpponentPlayer();

	for (Point point : points) {

	    if (point.getStone() != null && point.getStone().getColor() == opponentPlayer.getColor() && !point.isInMill())
		return true;

	}
	return false;
    }

    private boolean isValid(Point point) {
	final Player activePlayer = Controller.INSTANCE.getGameHandler().getActivePlayer();
	final Player opponentPlayer = Controller.INSTANCE.getGameHandler().getOpponentPlayer();

	if (point.getStone() == null)
	    return false;

	if (point.getStone().getColor() != opponentPlayer.getColor()) {
	    JOptionPane.showMessageDialog(Controller.INSTANCE.getGui(), "You should take stones from your opponent!");
	    return false;
	}

	if (point.isInMill()) {
	    JOptionPane.showMessageDialog(Controller.INSTANCE.getGui(), "You can't take a stone that is part of a mill!");
	    return false;
	}

	return true;
    }

    private void takeStoneFromPoint(Point point) {
	final Player opponentPlayer = Controller.INSTANCE.getGameHandler().getOpponentPlayer();
	point.setStone(null);
	opponentPlayer.decreaseStonesLeft();
    }

    private void trySendingTakePacket(Point point) {
	final GameHandler handler = Controller.INSTANCE.getGameHandler();

	if (handler instanceof SingleplayerGameHandler)
	    return;

	final MultiplayerGameHandler multiplayerHandler = ((MultiplayerGameHandler) handler);
	final NetworkHandler networkHandler = multiplayerHandler.getNetworkHandler();

	networkHandler.sendPacket(new TakePacket(point.getColumn(), point.getRow(), point.getCircle()));
    }

    private boolean testForLose() {
	final Player opponentPlayer = Controller.INSTANCE.getGameHandler().getOpponentPlayer();

	if (opponentPlayer.getStonesPut() == 9 && opponentPlayer.getStonesLeft() < 3)
	    return true;

	if (isOpponentSuffocated())
	    return true;

	return false;
    }

    private boolean isOpponentSuffocated() {
	final Player activePlayer = Controller.INSTANCE.getGameHandler().getActivePlayer();
	final Player opponentPlayer = Controller.INSTANCE.getGameHandler().getOpponentPlayer();
	final Point[] points = Controller.INSTANCE.getGameHandler().getPoints();

	// check if there's any free point the opponent could go to
	for (Point ownPoint : points) {

	    // check if point is owned by opponent
	    if (ownPoint.getStone() != null) {
		if (ownPoint.getStone().getColor() == opponentPlayer.getColor()) {

		    // check if there's a neighbour point which is free
		    for (Point point : points) {

			if (ownPoint.isNeighbourTo(point) && point.getStone() == null)
			    return false;
		    }
		}
	    }
	}
	return true;
    }

    private void endGame() {
	final Player activePlayer = Controller.INSTANCE.getGameHandler().getActivePlayer();
	Controller.INSTANCE.getGui().setStatus(String.format("%s has won the game", activePlayer.getColor().toString()));
	JOptionPane.showMessageDialog(Controller.INSTANCE.getGui(), String.format("%s has won the game!", activePlayer.getColor().toString()));
	Controller.INSTANCE.getGameHandler().setGameDone(true);
    }

    private void trySendingWinPacket() {
	final GameHandler handler = Controller.INSTANCE.getGameHandler();

	if (handler instanceof SingleplayerGameHandler)
	    return;

	final MultiplayerGameHandler multiplayerHandler = ((MultiplayerGameHandler) handler);
	final NetworkHandler networkHandler = multiplayerHandler.getNetworkHandler();

	networkHandler.sendPacket(new WinPacket(false));
    }

    private void tryChangingOpponentToJumpingState() {
	final GameHandler gameHandler = Controller.INSTANCE.getGameHandler();
	final Player opponentPlayer = gameHandler.getOpponentPlayer();

	if (opponentPlayer.getStonesPut() == 9 && opponentPlayer.getStonesLeft() < 4) {
	    // if singleplayer change to jump state, (keep only listening to packets in multiplayer)
	    if (!Controller.INSTANCE.getGameArguments().isMultiplayer())
		opponentPlayer.setCurrentState(PlayerStates.JUMP_STATE);
	    else
		((MultiplayerGameHandler) gameHandler).getNetworkHandler().sendPacket(new ChangeToJumpingStatePacket());

	    JOptionPane.showMessageDialog(Controller.INSTANCE.getGui(), String.format("%s has only 3 stones left. He can now jump!", opponentPlayer.getColor()
		.toString()));

	}
    }

    @Override
    public void onVoidClicked() {
    }

    @Override
    public void refreshStatus() {
	final Player activePlayer = Controller.INSTANCE.getGameHandler().getActivePlayer();
	final Player opponentPlayer = Controller.INSTANCE.getGameHandler().getOpponentPlayer();
	Controller.INSTANCE.getGui().setStatus(String.format("%s must take a stone from %s", activePlayer.getColor().toString(), opponentPlayer.getColor()
	    .toString()));
    }

}

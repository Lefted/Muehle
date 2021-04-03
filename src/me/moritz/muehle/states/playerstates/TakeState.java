package me.moritz.muehle.states.playerstates;

import javax.swing.JOptionPane;

import me.moritz.muehle.core.Controller;
import me.moritz.muehle.models.Player;
import me.moritz.muehle.models.Point;

public class TakeState implements PlayerState {

    private PlayerState nextState;

    public TakeState(PlayerState nextState) {
	this.nextState = nextState;

	// ensure that the stone that was placed latest is rendered
	Controller.INSTANCE.getGui().repaintGamePanel();
	
	// leave state implicitely if the player can't take a stone
	final boolean canTakeStone = checkCanTakeStone();

	if (!canTakeStone) {
	    final Player activePlayer = Controller.INSTANCE.getActivePlayer();
	    final Player opponentPlayer = Controller.INSTANCE.getOpponentPlayer();
	    JOptionPane.showMessageDialog(Controller.INSTANCE.getGui(), String.format("%s cannot take a stone from %s.", activePlayer.getColor().toString(),
		opponentPlayer.getColor().toString()));
	    activePlayer.setCurrentState(nextState);
	    Controller.INSTANCE.changePlayers();
	    return;
	}
    }

    @Override
    public void onPointClicked(Point point) {
	final Player activePlayer = Controller.INSTANCE.getActivePlayer();

	if (isValid(point)) {
	    takeStoneFromPoint(point);
	    final boolean opponentLost = testForLose();

	    if (opponentLost) {
		endGame();
	    } else {
		tryChangingOpponentToJumpingState();
		activePlayer.setCurrentState(nextState);
		Controller.INSTANCE.changePlayers();
	    }
	}
    }

    private boolean checkCanTakeStone() {
	final Point[] points = Controller.INSTANCE.getPoints();
	final Player opponentPlayer = Controller.INSTANCE.getOpponentPlayer();

	for (Point point : points) {

	    if (point.getStone() != null && point.getStone().getColor() == opponentPlayer.getColor() && !point.isInMill())
		return true;

	}
	return false;
    }

    private boolean isValid(Point point) {
	final Player activePlayer = Controller.INSTANCE.getActivePlayer();
	final Player opponentPlayer = Controller.INSTANCE.getOpponentPlayer();

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
	final Player opponentPlayer = Controller.INSTANCE.getOpponentPlayer();
	point.setStone(null);
	opponentPlayer.decreaseStonesLeft();
    }

    private boolean testForLose() {
	final Player opponentPlayer = Controller.INSTANCE.getOpponentPlayer();

	if (opponentPlayer.getStonesPut() == 9 && opponentPlayer.getStonesLeft() < 3)
	    return true;

	if (isOpponentSuffocated())
	    return true;

	return false;
    }

    private boolean isOpponentSuffocated() {
	final Player activePlayer = Controller.INSTANCE.getActivePlayer();
	final Player opponentPlayer = Controller.INSTANCE.getOpponentPlayer();
	final Point[] points = Controller.INSTANCE.getPoints();

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
	final Player activePlayer = Controller.INSTANCE.getActivePlayer();
	Controller.INSTANCE.getGui().setStatus(String.format("%s has won the game", activePlayer.getColor().toString()));
	JOptionPane.showMessageDialog(Controller.INSTANCE.getGui(), String.format("%s has won the game!", activePlayer.getColor().toString()));
	Controller.INSTANCE.setGameDone(true);
    }

    private void tryChangingOpponentToJumpingState() {
	final Player opponentPlayer = Controller.INSTANCE.getOpponentPlayer();

	if (opponentPlayer.getStonesPut() == 9 && opponentPlayer.getStonesLeft() < 4) {
	    JOptionPane.showMessageDialog(Controller.INSTANCE.getGui(), String.format("%s has only 3 stones left. He can now jump!", opponentPlayer.getColor()
		.toString()));
	    opponentPlayer.setCurrentState(PlayerStates.JUMP_STATE);
	} else {
	}
    }

    @Override
    public void onVoidClicked() {
    }

    @Override
    public void refreshStatus() {
	final Player activePlayer = Controller.INSTANCE.getActivePlayer();
	final Player opponentPlayer = Controller.INSTANCE.getOpponentPlayer();
	Controller.INSTANCE.getGui().setStatus(String.format("%s must take a stone from %s", activePlayer.getColor().toString(), opponentPlayer.getColor()
	    .toString()));
    }

}

package me.moritz.muehle.states.playerstates;

import me.moritz.muehle.core.Controller;
import me.moritz.muehle.models.Player;
import me.moritz.muehle.models.Point;

public class TakeState implements PlayerState {

    private PlayerState nextState;

    public TakeState(PlayerState nextState) {
	this.nextState = nextState;
    }

    @Override
    public void onPointClicked(Point point) {
	final Player activePlayer = Controller.INSTANCE.getActivePlayer();

	final boolean canTakeStone = checkCanTakeStone();
	
	if (!canTakeStone) {
	    activePlayer.setCurrentState(nextState);
	    Controller.INSTANCE.changePlayers();
	    return;
	}

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

	if (point.getStone().getColor() != opponentPlayer.getColor())
	    return false;

	if (point.isInMill())
	    return false;

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
	Controller.INSTANCE.setGameDone(true);
    }

    private void tryChangingOpponentToJumpingState() {
	final Player opponentPlayer = Controller.INSTANCE.getOpponentPlayer();

	System.out.println(String.format("stones put %s stones left %s", opponentPlayer.getStonesPut(), opponentPlayer.getStonesLeft()));

	if (opponentPlayer.getStonesPut() == 9 && opponentPlayer.getStonesLeft() < 4) {
	    opponentPlayer.setCurrentState(PlayerStates.JUMP_STATE);
	    System.out.println("change to jump state");
	} else {
	    System.out.println("no change to jump state");
	}
    }

    @Override
    public void onVoidClicked() {
    }

    @Override
    public void refreshStatus() {
	final Player activePlayer = Controller.INSTANCE.getActivePlayer();
	final Player opponentPlayer = Controller.INSTANCE.getOpponentPlayer();
	Controller.INSTANCE.getGui().setStatus(String.format("%s must take a stone from %s", activePlayer.getColor().toString(), opponentPlayer.getColor().toString()));
    }

}

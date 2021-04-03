package me.moritz.muehle.states.playerstates;

import me.moritz.muehle.core.Controller;
import me.moritz.muehle.models.Player;
import me.moritz.muehle.models.Point;
import me.moritz.muehle.models.Stone;

public class MoveState implements PlayerState {

    @Override
    public void onPointClicked(Point point) {
	final Player activePlayer = Controller.INSTANCE.getActivePlayer();
	final Point selectedPoint = activePlayer.getSelectedPoint();

	// select a point
	if (selectedPoint == null) {
	    final Stone stoneClicked = point.getStone();

	    if (stoneClicked != null && stoneClicked.getColor() == activePlayer.getColor()) {
		activePlayer.setSelectedPoint(point);
	    }
	} else {

	    if (point.getStone() == null) {
		if (isValidMovement(point, selectedPoint)) {

		    // move the stone
		    final boolean createdMill = moveStoneAndCheckForMill(selectedPoint, point);
		    if (createdMill) {

			// take a stone
			final PlayerState nextState = activePlayer.getCurrentState();
			activePlayer.setCurrentState(new TakeState(nextState));
		    } else {

			if (isOpponentSuffocated()) {
			    // check works
			    System.out.println("Sufffocated!");
			    // TODO
			}
			Controller.INSTANCE.changePlayers();
		    }

		    activePlayer.setSelectedPoint(null);
		}
	    } else if (point.getStone().getColor() == activePlayer.getColor() && point != selectedPoint) {
		// select another point
		activePlayer.setSelectedPoint(point);
	    } else {
		// deselect point
		activePlayer.setSelectedPoint(null);
	    }

	}
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

    protected boolean isValidMovement(Point origin, Point destination) {
	return origin.isNeighbourTo(destination);
    }

    protected boolean moveStoneAndCheckForMill(Point origin, Point destination) {
	final Stone stone = origin.getStone();
	destination.setStone(stone);
	origin.setStone(null);
	stone.setPoint(destination);

	boolean createdMill = destination.isInMill();
	return createdMill;
    }

    @Override
    public void onVoidClicked() {
	final Player activePlayer = Controller.INSTANCE.getActivePlayer();
	final Point selectedPoint = activePlayer.getSelectedPoint();

	if (selectedPoint != null)
	    activePlayer.setSelectedPoint(null);
    }

    @Override
    public void refreshStatus() {
	final Player activePlayer = Controller.INSTANCE.getActivePlayer();
	Controller.INSTANCE.getGui().setStatus(String.format("%s must move a stone", activePlayer.getColor().toString()));
    }
}

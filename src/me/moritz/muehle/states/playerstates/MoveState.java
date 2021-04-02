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

	if (selectedPoint == null) {
	    final Stone stoneClicked = point.getStone();

	    if (stoneClicked != null && stoneClicked.getColor() == activePlayer.getColor()) {
		activePlayer.setSelectedPoint(point);
	    }
	} else {

	    if (point.getStone() == null) {
		if (isValid(point, selectedPoint)) {
		    
		    final boolean createdMill = moveStoneAndCheckForMill(selectedPoint, point);
		    if (createdMill) {
			System.out.println("Made new mill!");
			// TODO
			// change to TakeState
		    }
		    
		    // TODO check if enemy player cannot move
		    
		    activePlayer.setSelectedPoint(null);
		    Controller.INSTANCE.changePlayers();
		}
	    } else if (point.getStone().getColor() == activePlayer.getColor() && point != selectedPoint) {
		activePlayer.setSelectedPoint(point);
	    } else {
		activePlayer.setSelectedPoint(null);
	    }

	}
    }

    protected  boolean isValid(Point origin, Point destination) {
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
}

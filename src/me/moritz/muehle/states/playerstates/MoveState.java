package me.moritz.muehle.states.playerstates;

import me.moritz.muehle.core.Controller;
import me.moritz.muehle.models.Player;
import me.moritz.muehle.models.Point;
import me.moritz.muehle.models.Stone;

public class MoveState implements PlayerState {

    @Override
    public void onClickedPoint(Point point) {
	final Player activePlayer = Controller.INSTANCE.getActivePlayer();
	final Point selectedPoint = activePlayer.getSelectedPoint();

	if (selectedPoint == null) {
	    final Stone stoneClicked = point.getStone();

	    if (stoneClicked != null && stoneClicked.getColor() == activePlayer.getColor()) {
		activePlayer.setSelectedPoint(point);
	    }
	} else {
	    // TODO validate
	    if (point.getStone() == null) {
		moveStone(selectedPoint, point);
		activePlayer.setSelectedPoint(null);
		Controller.INSTANCE.changePlayers();
	    } else if (point.getStone().getColor() == activePlayer.getColor()) {
		activePlayer.setSelectedPoint(point);
	    } else {
		activePlayer.setSelectedPoint(null);
	    }
	}
    }

    private void moveStone(Point origin, Point destination) {
	final Stone stone = origin.getStone();
	destination.setStone(stone);
	origin.setStone(null);
	stone.setPoint(destination);
    }

    @Override
    public void onVoidClicked() {
	final Player activePlayer = Controller.INSTANCE.getActivePlayer();
	final Point selectedPoint = activePlayer.getSelectedPoint();
	
	if (selectedPoint != null)
	    activePlayer.setSelectedPoint(null);
    }
}
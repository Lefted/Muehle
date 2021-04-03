package me.moritz.muehle.states.playerstates;

import me.moritz.muehle.core.Controller;
import me.moritz.muehle.models.Player;
import me.moritz.muehle.models.Point;

public class PutState implements PlayerState {

    @Override
    public void onPointClicked(Point point) {
	final Player activePlayer = Controller.INSTANCE.getActivePlayer();

	if (point.getStone() == null) {
	    final boolean createdMill = putStoneAndCheckForMill(point);

	    if (activePlayer.getStonesPut() == 9) {
		activePlayer.setCurrentState(PlayerStates.MOVE_STATE);
	    }

	    if (createdMill) {
		final PlayerState nextState = activePlayer.getCurrentState();
		activePlayer.setCurrentState(new TakeState(nextState));
	    } else {
		Controller.INSTANCE.changePlayers();
	    }
	}
    }

    private boolean putStoneAndCheckForMill(Point point) {
	final Player activePlayer = Controller.INSTANCE.getActivePlayer();

	point.placeStone(activePlayer.getColor());
	activePlayer.increaseStonesPut();
	activePlayer.increaseStonesLeft();

	if (point.isInMill())
	    return true;

	return false;
    }

    @Override
    public void onVoidClicked() {
    }

    @Override
    public void refreshStatus() {
	final Player activePlayer = Controller.INSTANCE.getActivePlayer();
	final int stonesLeftToPut = 9 - activePlayer.getStonesPut();
	Controller.INSTANCE.getGui().setStatus(String.format("%s must place a stone (%s stones left)", activePlayer.getColor().toString(), stonesLeftToPut));
    }

}

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

	// TODO
	// check if player can take any stone

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

	return false;
    }

    private void endGame() {
    // TODO
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

}

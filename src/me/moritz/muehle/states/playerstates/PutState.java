package me.moritz.muehle.states.playerstates;

import me.moritz.muehle.core.Controller;
import me.moritz.muehle.models.Player;
import me.moritz.muehle.models.Point;
import me.moritz.muehle.models.Stone;

public class PutState implements PlayerState {

    @Override
    public void onClickedPoint(Point point) {
	final Player activePlayer = Controller.INSTANCE.getActivePlayer();

	point.placeStone(activePlayer.getColor());
	activePlayer.increaseStonesPut();

	if (activePlayer.getStonesPut() > 9) {
	    activePlayer.setCurrentState(PlayerStates.MOVE_STATE);
	}

	Controller.INSTANCE.changePlayers();
    }

    @Override
    public void onVoidClicked() {
    }

}

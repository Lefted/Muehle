package me.moritz.muehle.states.playerstates;

import me.moritz.muehle.core.Controller;
import me.moritz.muehle.models.Player;
import me.moritz.muehle.models.Point;

public class JumpState extends MoveState {

    @Override
    protected boolean isValidMovement(Point origin, Point destination) {
	return true;
    }

    @Override
    public void refreshStatus() {
	final Player activePlayer = Controller.INSTANCE.getActivePlayer();
	Controller.INSTANCE.getGui().setStatus(String.format("%s can jump with a stone", activePlayer.getColor().toString()));

    }
}

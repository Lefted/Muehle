package me.moritz.muehle.states.playerstates;

import me.moritz.muehle.models.Point;

public class JumpState extends MoveState{

    
    @Override
    protected boolean isValidMovement(Point origin, Point destination) {
	return true;
    }
}

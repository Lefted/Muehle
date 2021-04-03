package me.moritz.muehle.models;

import java.util.stream.IntStream;

import me.moritz.muehle.states.playerstates.PlayerState;

public class Player {

    private Color color;

    private int stonesLeft;
    private int stonesPut;
    private boolean canJump;

    private Point selectedPoint; // point from where a stone should be moved

    private PlayerState currentState;

    public Player(Color color) {
	this.color = color;

	stonesLeft = 0;
	stonesPut = 0;
	canJump = false;
    }

    public int getStonesLeft() {
	return stonesLeft;
    }

    public int getStonesPut() {
	return stonesPut;
    }

    public void increaseStonesPut() {
	stonesPut++;
    }

    public void decreaseStonesLeft() {
	this.stonesLeft--;
    }
    
    public void increaseStonesLeft() {
	this.stonesLeft++;
    }

    public boolean isCanJump() {
	return canJump;
    }

    public void setCanJump(boolean canJump) {
	this.canJump = canJump;
    }

    public PlayerState getCurrentState() {
	return currentState;
    }

    public void setCurrentState(PlayerState currentState) {
	this.currentState = currentState;
	currentState.refreshStatus();
    }

    public Color getColor() {
	return color;
    }

    public Point getSelectedPoint() {
	return selectedPoint;
    }

    public void setSelectedPoint(Point selectedPoint) {
	this.selectedPoint = selectedPoint;
    }

}

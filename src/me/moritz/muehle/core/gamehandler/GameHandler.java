package me.moritz.muehle.core.gamehandler;

import me.moritz.muehle.core.Gui;
import me.moritz.muehle.models.Player;
import me.moritz.muehle.models.Point;

public abstract class GameHandler implements IGameHandler {

    protected Gui gui;
    protected Player[] players;
    protected Point[] points;
    
    protected int activePlayerIdx;
    protected boolean gameDone;

    protected void createPoints() {
	points = new Point[24];
	points[0] = new Point(0, 0, 0);
	points[1] = new Point(0, 0, 1);
	points[2] = new Point(0, 0, 2);

	points[3] = new Point(0, 1, 0);
	points[4] = new Point(0, 1, 1);
	points[5] = new Point(0, 1, 2);

	points[6] = new Point(0, 2, 0);
	points[7] = new Point(0, 2, 1);
	points[8] = new Point(0, 2, 2);

	points[9] = new Point(1, 0, 0);
	points[10] = new Point(1, 0, 1);
	points[11] = new Point(1, 0, 2);

	points[12] = new Point(1, 2, 0);
	points[13] = new Point(1, 2, 1);
	points[14] = new Point(1, 2, 2);

	points[15] = new Point(2, 0, 0);
	points[16] = new Point(2, 0, 1);
	points[17] = new Point(2, 0, 2);

	points[18] = new Point(2, 1, 0);
	points[19] = new Point(2, 1, 1);
	points[20] = new Point(2, 1, 2);

	points[21] = new Point(2, 2, 0);
	points[22] = new Point(2, 2, 1);
	points[23] = new Point(2, 2, 2);
    }

    protected void removeStonesFromField() {
	for (Point point : points) {
	    point.setStone(null);
	}
    }

    public void changePlayers() {
	activePlayerIdx = activePlayerIdx == 0 ? 1 : 0;
	getActivePlayer().getCurrentState().refreshStatus();
    }

    public Player[] getPlayers() {
	return players;
    }

    public int getActivePlayerIdx() {
	return activePlayerIdx;
    }

    public Player getActivePlayer() {
	return players[activePlayerIdx];
    }

    public Player getOpponentPlayer() {
	final int opponentIdx = activePlayerIdx == 0 ? 1 : 0;
	return players[opponentIdx];
    }

    public boolean isGameDone() {
	return gameDone;
    }

    public void setGameDone(boolean gameDone) {
	this.gameDone = gameDone;
    }

    public Gui getGui() {
	return gui;
    }

    public void setGui(Gui gui) {
	this.gui = gui;
    }

    public Point[] getPoints() {
	return points;
    }

    public Point getPointAt(int column, int row, int circle) {
	for (Point point : points) {
	    if (point.getColumn() == column && point.getRow() == row && point.getCircle() == circle)
		return point;
	}

	return null;
    }
}

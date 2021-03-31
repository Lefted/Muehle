package me.moritz.muehle.core;

import java.awt.EventQueue;
import java.util.stream.IntStream;

import me.moritz.muehle.models.Color;
import me.moritz.muehle.models.Player;
import me.moritz.muehle.models.Point;
import me.moritz.muehle.states.playerstates.PlayerStates;

public class Controller {

    // SINGLETON INSTANCE
    public static final Controller INSTANCE = new Controller();
    
    private Gui gui;
    private Player[] players;
    private int activePlayerIdx;

    private Point[] points;

    public static void main(String[] args) {
	// create the GUI (ensuring it lives on the Event-Dispatch-Thread)
	EventQueue.invokeLater(() -> {
	    final Gui gui = new Gui();
	    INSTANCE.setGui(gui);
	});
    }

    public Controller() {
	createPoints();

	// create players
	players = new Player[2];
	players[0] = new Player(Color.WHITE);
	players[1] = new Player(Color.BLACK);

	initializeNewGame();
    }

    private void createPoints() {
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

    public void initializeNewGame() {
	removeStonesFromField();

	// put both players into PUT_STATE
	IntStream.range(0, 2).forEach((i) -> players[i].setCurrentState(PlayerStates.PUT_STATE));
    }

    private void removeStonesFromField() {
	for (Point point : points) {
	    point.setStone(null);
	}
    }

    public void changePlayers() {
	activePlayerIdx = activePlayerIdx == 0 ? 1 : 0;
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
}

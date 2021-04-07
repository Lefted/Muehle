package me.moritz.muehle.multiplayer.core;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import me.moritz.muehle.core.Gui;
import me.moritz.muehle.models.Color;
import me.moritz.muehle.models.Player;
import me.moritz.muehle.models.Point;
import me.moritz.muehle.multiplayer.network.ClientNetworkHandler;
import me.moritz.muehle.multiplayer.network.NetworkHandler;
import me.moritz.muehle.multiplayer.network.ServerNetworkHandler;
import me.moritz.muehle.multiplayer.network.packets.TestPacket;
import me.moritz.muehle.states.playerstates.PlayerStates;

public class MultiplayerController {

    // SINGLETON INSTANCE
    public static final MultiplayerController INSTANCE = new MultiplayerController();

    private NetworkHandler networkHandler;

    private Gui gui;
    private Player[] players;
    private int activePlayerIdx;

    private Point[] points;

    private boolean gameDone;

    public static void main(String[] args) {

	INSTANCE.doGame(args);

	// TODO
	// // create the GUI (ensuring it lives on the Event-Dispatch-Thread)
	// EventQueue.invokeLater(() -> {
	// final Gui gui = new Gui();
	// INSTANCE.setGui(gui);
	// INSTANCE.initializeNewGame();
	// });
    }

    public void doGame(String[] args) {
	final List<String> arguments = Arrays.asList(args);
	boolean isServer = false;
	boolean valid = true;
	String ip = null;
	int port = 0;

	if (arguments.stream().anyMatch((arg) -> arg.equalsIgnoreCase("server")))
	    isServer = true;

	if (!arguments.stream().anyMatch((arg) -> arg.contains("ip="))) {
	    valid = false;
	} else {
	    String ipArg = arguments.stream().filter((arg) -> arg.contains("ip")).findAny().get();
	    ip = ipArg.replace("ip=", "");
	}

	if (!arguments.stream().anyMatch((arg) -> arg.contains("port="))) {
	    valid = false;
	} else {
	    String portArg = arguments.stream().filter((arg) -> arg.contains("port=")).findFirst().get();
	    port = Integer.parseInt(portArg.replace("port=", ""));
	}

	if (valid) {
	    
	    System.out.println(isServer ? "Server" : "Client");
	    System.out.println(String.format("%s:%s", ip, port));
	    
	    networkHandler = isServer ? new ServerNetworkHandler(ip, port) : new ClientNetworkHandler(ip, port);
	    networkHandler.runThread();
	}

	
	while (true) {
	}
    }

    public MultiplayerController() {
	createPoints();

	// create players
	players = new Player[2];
	players[0] = new Player(Color.WHITE);
	players[1] = new Player(Color.BLACK);

	// initializeNewGame();
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
	gameDone = false;
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
	getActivePlayer().getCurrentState().refreshStatus();
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
}

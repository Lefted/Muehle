package me.moritz.muehle.core;

import java.awt.EventQueue;

import me.moritz.muehle.arguments.ArgumentParser;
import me.moritz.muehle.arguments.GameArguments;
import me.moritz.muehle.core.gamehandler.GameHandler;
import me.moritz.muehle.core.gamehandler.MultiplayerGameHandler;
import me.moritz.muehle.core.gamehandler.SingleplayerGameHandler;

public class Controller {

    // SINGLETON INSTANCE
    public static final Controller INSTANCE = new Controller();

    private Gui gui;

    private GameArguments gameArguments;
    private GameHandler gameHandler;

    public static void entry(GameArguments args) {

	INSTANCE.gameArguments = args;
	INSTANCE.createGameHandler();

	INSTANCE.getGameHandler().setupGame();

	// create the GUI (ensuring it lives on the Event-Dispatch-Thread)
	EventQueue.invokeLater(() -> {
	    final Gui gui = new Gui();
	    INSTANCE.setGui(gui);

	    if (INSTANCE.getGameHandler().shouldSetupNewRound())
		INSTANCE.getGameHandler().setupNewRound();
	});
    }

    private void parseArguments(String[] args) {
	final ArgumentParser parser = new ArgumentParser(args);
	gameArguments = parser.switchPojo(GameArguments.class);
    }

    private void createGameHandler() {
	gameHandler = gameArguments.isOnline() ? new MultiplayerGameHandler() : new SingleplayerGameHandler();
    }

    public void setGameArguments(GameArguments gameArguments) {
	this.gameArguments = gameArguments;
    }

    public GameArguments getGameArguments() {
	return gameArguments;
    }

    public GameHandler getGameHandler() {
	return gameHandler;
    }

    public Gui getGui() {
	return gui;
    }

    public void setGui(Gui gui) {
	this.gui = gui;
    }
}
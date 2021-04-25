package me.moritz.muehle.core;

import java.awt.EventQueue;

import javax.swing.JOptionPane;

import me.moritz.muehle.core.gamehandler.GameHandler;
import me.moritz.muehle.core.gamehandler.LocalMultiplayerGameHandler;
import me.moritz.muehle.core.gamehandler.OnlineMultiplayerGameHandler;
import me.moritz.muehle.exceptions.ResourceLocationException;
import me.moritz.muehle.settings.GameSettings;

public class Controller {

    // SINGLETON INSTANCE
    public static final Controller INSTANCE = new Controller();

    private Gui gui;

    private GameSettings gameSettings;
    private GameHandler gameHandler;

    public static void entry(GameSettings args) {

	INSTANCE.gameSettings = args;
	INSTANCE.createGameHandler();

	try {
	    INSTANCE.getGameHandler().loadResources();
	} catch (ResourceLocationException e) {
	    // e.printStackTrace();
	    JOptionPane.showMessageDialog(SettingsGui.getInstance().getFrame(), e.getMessage(), "Resource Loading Exception", JOptionPane.ERROR_MESSAGE);
	    SettingsGui.getInstance().getFrame().setVisible(true);
	    return;
	}

	INSTANCE.getGameHandler().setupGame();

	// create the GUI (ensuring it lives on the Event-Dispatch-Thread)
	EventQueue.invokeLater(() -> {
	    final Gui gui = new Gui();
	    INSTANCE.setGui(gui);

	    if (INSTANCE.getGameHandler().shouldSetupNewRound())
		INSTANCE.getGameHandler().setupNewRound();
	});
    }

    private void createGameHandler() {
	gameHandler = gameSettings.isOnline() ? new OnlineMultiplayerGameHandler() : new LocalMultiplayerGameHandler();
    }

    public void setGameSettings(GameSettings gameSettings) {
	this.gameSettings = gameSettings;
    }

    public GameSettings getGameSettings() {
	return gameSettings;
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
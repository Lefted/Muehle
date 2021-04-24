package me.moritz.muehle.core;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import me.moritz.muehle.arguments.OnlineMultiplayerGameArguments;

public class Gui extends JFrame {

    public static final int PANE_SIZE = 850;

    private JPanel gamePanel;

    public Gui() {
	initialize();
	postInitialize();
    }

    private void initialize() {
	setTitle("M\u00FChle");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setSize(PANE_SIZE, PANE_SIZE);
	setResizable(false);

	setVisible(true);
    }

    private void postInitialize() {
	// create the panel where things are drawn
	gamePanel = new GamePanel();
	add(gamePanel);

	setLocationRelativeTo(null);
	try {
	    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
	    e.printStackTrace();
	}
	SwingUtilities.updateComponentTreeUI(this);
    }

    public void setStatus(String status) {
	String prefix = null;

	if (Controller.INSTANCE.getGameArguments().isOnline()) {
	    final OnlineMultiplayerGameArguments args = (OnlineMultiplayerGameArguments) Controller.INSTANCE.getGameArguments();
	    prefix = args.isServer() ? "Mühle - Server" : "Mühle - Client";
	} else
	    prefix = "Mühle -";

	setTitle(String.format("%s %s", prefix, status));
    }

    public void repaintGamePanel() {
	gamePanel.repaint();
    }
}

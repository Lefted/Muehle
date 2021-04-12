package me.moritz.muehle.core;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

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

	if (Controller.getInstance().getGameArguments().isMultiplayer())
	    prefix = Controller.getInstance().getGameArguments().isServer() ? "Mühle - Server" : "Mühle - Client";
	else
	    prefix = "Mühle -";

	setTitle(String.format("%s %s", prefix, status));
    }

    public void repaintGamePanel() {
	gamePanel.repaint();
    }
}

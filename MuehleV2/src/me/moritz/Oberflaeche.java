package me.moritz;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class Oberflaeche extends JFrame {

    // KONSTANTEN
    public static final int FRAME_WIDTH = 800;
    public static final int FRAME_HEIGHT = 600;

    // ATTRIBUTE
    private JPanel contentPane;
    private Steuerung dieSteuerung;

    // DEBUG
    private int redX = 50;

    private int redY = 50;

    public int getRedX() {
	return redX;
    }

    public int getRedY() {
	return redY;
    }

    // KONSTRUKTOR
    public Oberflaeche(Steuerung dieSteuerung) {
	this.dieSteuerung = dieSteuerung;
    }

    // CREATE
    public void createAndShowGui() {
	System.out.println("Is Gui created on EDT? " + SwingUtilities.isEventDispatchThread());

	setTitle("Mühle V1");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	JButton btnNewButton = new JButton("Ein Button");
	btnNewButton.setBounds(131, 382, 89, 23);
	btnNewButton.setFocusable(false);

	// DEBUG
	addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_D:
		    redX++;
		    break;

		case KeyEvent.VK_S:
		    redY++;
		    break;
		}
	    }
	});

	contentPane = new Spielfeld(this);
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	contentPane.setLayout(null);
	setContentPane(contentPane);
	contentPane.add(btnNewButton);

	// Fenstergröße je nach Spielfeldgröße setzen
	pack();
	setLocationRelativeTo(null);
	setVisible(true);
    }
}

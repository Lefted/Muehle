package me.moritz;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

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
	// addKeyListener(new KeyAdapter() {
	// @Override
	// public void keyPressed(KeyEvent e) {
	// switch (e.getKeyCode()) {
	// case KeyEvent.VK_D:
	// redX++;
	// break;
	//
	// case KeyEvent.VK_S:
	// redY++;
	// break;
	// }
	// }
	// });

	addKeyListener(this.dieSteuerung.getKeyadapter());

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

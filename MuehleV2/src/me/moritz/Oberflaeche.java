package me.moritz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

public class Oberflaeche extends JFrame {

    // KONSTANTEN
    public static final int PANE_WIDTH = 960;
    public static final int PANE_HEIGHT = 960;

    // ATTRIBUTE
    private JPanel contentPane;
    private Steuerung dieSteuerung;
    private JButton btnSpielStarten;

    // KONSTRUKTOR
    public Oberflaeche(Steuerung dieSteuerung) {
	this.dieSteuerung = dieSteuerung;
    }

    // METHODEN
    public void createAndShowGui() {
	// DEBUG
	System.out.println("Wurde die GUI auf dem EDT erstellt? " + SwingUtilities.isEventDispatchThread());

	setTitle("Mühle V1");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setResizable(false);

	// Components
	btnSpielStarten = new JButton("Spiel starten");
	btnSpielStarten.setBounds(300, 180, 200, 40);
	btnSpielStarten.setFocusable(false);	// damit der KeyListener richtig funktioniert
	btnSpielStarten.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		dieSteuerung.setSpielzustandSpielen();
	    }
	});

	// Spielfeld
	contentPane = new Spielfeld(this);
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	contentPane.setLayout(null);
	setContentPane(contentPane);
	contentPane.add(btnSpielStarten);

	// Fenstergröße je nach Spielfeldgröße setzen
	pack();
	// Fenster in die Mite des Screens
	setLocationRelativeTo(null);
	setVisible(true);

	// schöneres Aussehen anwenden
	try {
	    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
	    e.printStackTrace();
	}
	SwingUtilities.updateComponentTreeUI(this);
    }

    public Steuerung getDieSteuerung() {
	return dieSteuerung;
    }

    public JButton getBtnSpielStarten() {
	return btnSpielStarten;
    }
}

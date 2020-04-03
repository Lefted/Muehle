package me.moritz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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
    private JRadioButton btnFarbwahl1; // Spieler 1 hat schwarze Steine, 2 weiße
    private JRadioButton btnFarbwahl2; // Spieler 2 hat weiße Steine, 1 schwarze

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
	// Starten Button
	btnSpielStarten = new JButton("Spiel starten");
	btnSpielStarten.setBounds(300, 180, 200, 40);
	btnSpielStarten.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		dieSteuerung.setSpielzustandFarbwahl();
	    }
	});

	// Farbwahl Radiobuttons
	btnFarbwahl1 = new JRadioButton("Spieler 1 schwarze Steine, Spieler 2 weiße Steine");
	btnFarbwahl2 = new JRadioButton("Spieler 1 weiße Steine, Spieler 2 schwarze Steine");
	btnFarbwahl1.setBounds(40, 40, 260, 20);
	btnFarbwahl2.setBounds(40, 60, 260, 20);

	// DEBUG
	btnFarbwahl2.addActionListener(new ActionListener() {
	    
	    @Override
	    public void actionPerformed(ActionEvent e) {
		dieSteuerung.setSpielzustandSpielen();
	    }
	});
	
	ButtonGroup groupFarbwahl = new ButtonGroup();
	groupFarbwahl.add(btnFarbwahl1);
	groupFarbwahl.add(btnFarbwahl2);

	// Spielfeld
	contentPane = new Spielfeld(this);
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	contentPane.setLayout(null);
	setContentPane(contentPane);
	// Components hinzufügen
	contentPane.add(btnSpielStarten);
	contentPane.add(btnFarbwahl1);
	contentPane.add(btnFarbwahl2);

	// Spielzustand anfangs auf Menü setzen
	dieSteuerung.setSpielzustandMenu();

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

    public JRadioButton getBtnFarbwahl1() {
	return btnFarbwahl1;
    }

    public JRadioButton getBtnFarbwahl2() {
	return btnFarbwahl2;
    }
}

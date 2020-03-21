package me.moritz;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.SwingUtilities;

public class Steuerung implements Runnable {

    // KONSTANTEN
    public static int FPS_RATE = 60; // Aktualisierungen pro Sekunde

    // ATTRIBUTE
    private Oberflaeche dieOberflaeche;
    private Spielzustand spielzustand = Spielzustand.MENU;

    // KONSTRUKTOR
    public Steuerung() {
	this.dieOberflaeche = new Oberflaeche(this);
    }

    // METHODEN
    @Override
    public void run() {
	// DEBUG
	System.out.println("Wurde das Spiel auf dem EDT erstellt? " + SwingUtilities.isEventDispatchThread());
	// Das Spiel der FPS-Rate nach aktualisieren
	double timePerTick = 1000000000 / FPS_RATE;
	double delta = 0;
	long now;
	long lastTime = System.nanoTime();

	while (true) {
	    now = System.nanoTime();
	    delta += (now - lastTime) / timePerTick;
	    lastTime = now;

	    if (delta >= 1) {
		// Spielogik
		this.tick();
		// Zeichen siehe Spielfeld.paintComponent()
		this.dieOberflaeche.repaint();
		delta--;
	    }
	}
    }

    // Spiellogik wird jeden Zyklus aufgerufen
    private void tick() {

    }

    public Oberflaeche getDieOberflaeche() {
	return dieOberflaeche;
    }

    // setzt den Spielzustand auf Spielen und versteckt das Menu
    public void setSpielzustandSpielen() {
	this.dieOberflaeche.getBtnSpielStarten().setVisible(false);
	this.spielzustand = Spielzustand.SPIELEN;
    }
    
    public Spielzustand getSpielzustand() {
	return this.spielzustand;
    }
}

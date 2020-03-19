package me.moritz;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.SwingUtilities;

public class Steuerung implements Runnable {

    // KONSTANTEN
    public static int FPS_RATE = 60; // Aktualisierungen pro Sekunde

    // ATTRIBUTE
    private Oberflaeche dieOberflaeche;
    private KeyAdapter keyadapter;

    // KONSTRUKTOR
    public Steuerung() {
	this.dieOberflaeche = new Oberflaeche(this);
	// tastatur
	this.keyadapter = new KeyAdapter() {
	    @Override
	    public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_SPACE:
		    System.out.println("space");
		    break;
		}
	    }
	};
    }

    // METHODEN
    @Override
    public void run() {
	System.out.println("Is game thread EDT? " + SwingUtilities.isEventDispatchThread());
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
		// tick();
		this.dieOberflaeche.repaint();
		delta--;
	    }
	}
    }

    public Oberflaeche getDieOberflaeche() {
	return dieOberflaeche;
    }

    public KeyAdapter getKeyadapter() {
	return this.keyadapter;
    }
}

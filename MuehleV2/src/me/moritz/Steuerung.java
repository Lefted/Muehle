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
	dieOberflaeche = new Oberflaeche(this);
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
		tick();
		// Zeichen siehe Spielfeld.paintComponent()
		dieOberflaeche.repaint();
		delta--;
	    }
	}
    }

    // Spiellogik wird jeden Zyklus aufgerufen
    private void tick() {

    }

    // setzt den Spielzustand auf Farbwahl und stellt die Components ein
    public void setSpielzustandFarbwahl() {
	dieOberflaeche.getBtnSpielStarten().setVisible(false);
	dieOberflaeche.getBtnFarbwahl2().setVisible(true);
	dieOberflaeche.getBtnFarbwahl1().setVisible(true);
	dieOberflaeche.getBtnFarbwahlBestaetigen().setVisible(true);
	
	// Standartwahl Spieler 1 schwarz, Spieler 2 weiﬂ
	dieOberflaeche.getBtnFarbwahl1().setSelected(true);

	spielzustand = Spielzustand.FARBWAHL;
    }

    // setzt den Spielzustand auf Spielen und versteckt die Farbwahl
    public void setSpielzustandSpielen() {
	dieOberflaeche.getBtnFarbwahl2().setVisible(false);
	dieOberflaeche.getBtnFarbwahl1().setVisible(false);
	dieOberflaeche.getBtnFarbwahlBestaetigen().setVisible(false);

	spielzustand = Spielzustand.SPIELEN;
    }

    // setzt den Spielzustand auf Menu und stellt die Components ein
    public void setSpielzustandMenu() {
	dieOberflaeche.getBtnFarbwahl1().setVisible(false);
	dieOberflaeche.getBtnFarbwahl2().setVisible(false);
	dieOberflaeche.getBtnFarbwahlBestaetigen().setVisible(false);
	
	spielzustand = Spielzustand.MENU;
    }

    public Spielzustand getSpielzustand() {
	return spielzustand;
    }

    public Oberflaeche getDieOberflaeche() {
	return dieOberflaeche;
    }
}

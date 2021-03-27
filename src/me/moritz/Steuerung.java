package me.moritz;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.SwingUtilities;

public class Steuerung implements Runnable {

    // KONSTANTEN
    public static int FPS_RATE = 60; // Aktualisierungen pro Sekunde

    // ATTRIBUTE
    private Oberflaeche dieOberflaeche;
    private Zustand menuzustand = Zustand.MENU;
    private Zustand spielzustand;
    private Spieler spieler1;
    private Spieler spieler2;

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

    // setzt den Menuzustand auf Einstellungen (Farbwahl,Startreihenfolge) und stellt die Components ein
    public void setMenuzustandEinstellungen() {
	dieOberflaeche.getBtnSpielStarten().setVisible(false);
	dieOberflaeche.getBtnFarbwahl2().setVisible(true);
	dieOberflaeche.getBtnFarbwahl1().setVisible(true);
	dieOberflaeche.getBtnStartreihenfolge1().setVisible(true);
	dieOberflaeche.getBtnStartreihenfolge2().setVisible(true);
	dieOberflaeche.getBtnEinstellungenBestaetigen().setVisible(true);
	dieOberflaeche.getMeldung().setVisible(false);
	
	// Standartwahl der Farbwahl: Spieler 1 schwarz, Spieler 2 weiß
	dieOberflaeche.getBtnFarbwahl1().setSelected(true);
	// Standartwahl der Startreihenfolge: Spieler 1 beginnt
	dieOberflaeche.getBtnStartreihenfolge1().setSelected(true);

	menuzustand = Zustand.EINSTELLUNGEN;
    }

    // setzt den Menuzustand auf Spielen und versteckt die Farbwahl
    public void setMenuzustandSpielen() {
	dieOberflaeche.getBtnFarbwahl2().setVisible(false);
	dieOberflaeche.getBtnFarbwahl1().setVisible(false);
	dieOberflaeche.getBtnStartreihenfolge1().setVisible(false);
	dieOberflaeche.getBtnStartreihenfolge2().setVisible(false);
	dieOberflaeche.getBtnEinstellungenBestaetigen().setVisible(false);
	dieOberflaeche.getMeldung().setVisible(true);

	menuzustand = Zustand.SPIELEN;
    }

    // setzt den Menuzustand auf Menu, stellt die Components ein und startet das Setzen
    public void setSpielzustandMenu() {
	dieOberflaeche.getBtnFarbwahl1().setVisible(false);
	dieOberflaeche.getBtnFarbwahl2().setVisible(false);
	dieOberflaeche.getBtnStartreihenfolge1().setVisible(false);
	dieOberflaeche.getBtnStartreihenfolge2().setVisible(false);
	dieOberflaeche.getBtnEinstellungenBestaetigen().setVisible(false);
	dieOberflaeche.getMeldung().setVisible(false);

	initSpiel();
	menuzustand = Zustand.MENU;
    }

    // setzt den Spielzustand und erstellt die Spieler Objekte
    private void initSpiel() {
	spieler1 = new Spieler(dieOberflaeche.getBtnFarbwahl1().isSelected() ? Farbe.SCHWARZ : Farbe.WEISS);
	spieler2 = new Spieler(dieOberflaeche.getBtnFarbwahl1().isSelected() ? Farbe.WEISS : Farbe.SCHWARZ);

	spielzustand = dieOberflaeche.getBtnStartreihenfolge1().isSelected() ? Zustand.SETZENSPIELER1 : Zustand.SEZTENSPIELER2;
    }

    // Verhalten, wenn ein Knotenpunkt bzw. ein Stein angeklickt wurde, übergibt den jeweiligen Knotenpunkt
    public void knotenpunktGeklickt(Knotenpunkt knotenpunkt) {
	// Steine setzen
	if (spielzustand == Zustand.SETZENSPIELER1) {
	    setzeStein(spieler1, knotenpunkt);
	} else if (spielzustand == Zustand.SEZTENSPIELER2) {
	    setzeStein(spieler2, knotenpunkt);
	}
    }

    // setzt einen Stein auf den Knotenpunkt und wechselt den Spielzustand
    // gibt eine Fehlermeldung aus, wenn dort bereits ein Stein ist
    private void setzeStein(Spieler spieler, Knotenpunkt knotenpunkt) {
	if (knotenpunkt.getStein() == null) {
	    knotenpunkt.setStein(new Stein(spieler.getFarbe()));
	    spieler.reduziereVerbleibendeSteine();

	    // Hat der andere Spieler noch Steine übrig?
	    Spieler andererSpieler = (spielzustand == Zustand.SETZENSPIELER1) ? spieler2 : spieler1;
	    if (andererSpieler.getVerbleibendeSteine() > 0) {
		// wenn noch Steine übrig sind, in Setzen wechseln
		spielzustand = (spielzustand == Zustand.SETZENSPIELER1) ? Zustand.SEZTENSPIELER2 : Zustand.SETZENSPIELER1;
	    } else {
		// wenn keine Steine übrig sind, in Bewegen wechseln
		spielzustand = (spielzustand == Zustand.SETZENSPIELER1) ? Zustand.BEWEGENSPIELER2 : Zustand.BEWEGENSPIELER1;
	    }
	} else {
	    // TODO zeige Fehlermeldung
	    dieOberflaeche.zeigeMeldung("An dieser Stelle liegt bereits ein Stein!");
	}
    }

    public Zustand getMenuzustand() {
	return menuzustand;
    }

    public Zustand getSpielzustand() {
	return spielzustand;
    }

    public Oberflaeche getDieOberflaeche() {
	return dieOberflaeche;
    }

    public Spieler getSpieler1() {
	return spieler1;
    }

    public Spieler getSpieler2() {
	return spieler2;
    }
}

package me.moritz;

public class Spieler {

    // ATTRIBUTE
    private Farbe farbe;
    private int verbleibendeSteine = 9; // Anfangs hat man 9 Steine
    private boolean springen = false; // Ab 3 Steinen kann man springen

    // KONSTRUKTOR
    public Spieler(Farbe farbe) {
	this.farbe = farbe;
    }

    // METHODEN
    public Farbe getFarbe() {
	return farbe;
    }

    public int getVerbleibendeSteine() {
	return verbleibendeSteine;
    }

    public void setVerbleibendeSteine(int verbleibendeSteine) {
	this.verbleibendeSteine = verbleibendeSteine;
    }

    public boolean isSpringen() {
	return springen;
    }

    public void setSpringen(boolean springen) {
	this.springen = springen;
    }
}

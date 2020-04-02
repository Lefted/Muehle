package me.moritz;

import java.awt.Color;
import java.awt.Graphics;

public class Knotenpunkt {

    // KONSTANTEN
    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;

    // ATTRIBUTE
    private int x;
    private int y;
    private Color farbe;
    private Stein stein;
    private Spielfeld dasSpielfeld;

    // DEBUG
    static int i = 0;
    
    // KONSTRUKTOR
    public Knotenpunkt(Spielfeld dasSpielfeld, int x, int y) {
	this.dasSpielfeld = dasSpielfeld;
	this.x = x;
	this.y = y;
	farbe = new Color(1F, 1F, 1F, 0.5F);

	// DEBUG
	if (i < 4 && i != 2) {
	    stein = new Stein(Farbe.WEISS);
	} else if (i != 2){
	    stein = new Stein(Farbe.SCHWARZ);
	}
	i++;
    }

    // METHODEN
    // gibt zurück, ob die Maus über dem Knotenpunkt ist
    public boolean istMausÜber(int mausX, int mausY) {
	final boolean flag1 = (mausX) > x && (mausX < (x + WIDTH));
	final boolean flag2 = (mausY) > y && (mausY < (y + HEIGHT));
	return flag1 && flag2;
    }

    // DEBUG
    public void zeichnen(Graphics g) {
	// wenn stein auf knotenpunkt ist, zeichnen
	if (stein != null) {
	    stein.zeichnen(dasSpielfeld, g, x, y);
	}
	
	g.setColor(farbe);
	g.fillRect(x, y, WIDTH, HEIGHT);
    }

    public int getX() {
	return x;
    }

    public int getY() {
	return y;
    }

    public Color getFarbe() {
	return farbe;
    }

    public void setFarbe(Color farbe) {
	this.farbe = farbe;
    }

    public Stein getStein() {
	return stein;
    }

    public void setStein(Stein stein) {
	this.stein = stein;
    }
}

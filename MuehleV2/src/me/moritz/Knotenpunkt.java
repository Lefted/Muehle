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

    // KONSTRUKTOR
    public Knotenpunkt(int x, int y) {
	super();
	this.x = x;
	this.y = y;

	// DEBUG
	farbe = new Color(1F, 1F, 1F, 0.5F);
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
}

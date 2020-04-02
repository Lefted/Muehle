package me.moritz;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Stein {

    // KONSTANTEN
    private static BufferedImage imgSteinSchwarz = Utilities.ladeBild("/steinSchwarz3.png");
    private static BufferedImage imgSteinWeiss = Utilities.ladeBild("/steinWeiﬂ2.png");

    // ATTRIBUTE
    private final Farbe farbe;

    // KONSTRUKTOR
    public Stein(Farbe farbe) {
	this.farbe = farbe;
    }

    // METHODEN
    public void zeichnen(Spielfeld dasSpielfeld, Graphics g, int x, int y) {
	if (this.farbe == Farbe.SCHWARZ) {
	    g.drawImage(imgSteinSchwarz, x, y, 64, 64, dasSpielfeld);
	} else if (this.farbe == Farbe.WEISS) {
	    g.drawImage(imgSteinWeiss, x, y, 64, 64, dasSpielfeld);
	}
    }
    
    public Farbe getFarbe() {
	return farbe;
    }
}

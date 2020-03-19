package me.moritz;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Spielfeld extends JPanel {

    // ATTRIBUTE
    Oberflaeche dieOberflaeche;

    // KONSTRUKTOR
    public Spielfeld(Oberflaeche dieOberflaeche) {
	super();
	this.dieOberflaeche = dieOberflaeche;
    }

    // METHODEN
    @Override
    public Dimension getPreferredSize() {
	return new Dimension(Oberflaeche.FRAME_WIDTH, Oberflaeche.FRAME_HEIGHT);
    }

    @Override
    protected void paintComponent(Graphics g) {
	// Hintergrund zeichnen
	super.paintComponent(g);

	g.clearRect(0, 0, Oberflaeche.FRAME_WIDTH, Oberflaeche.FRAME_HEIGHT);

	g.setColor(Color.RED);
	g.fillRect(this.dieOberflaeche.getRedX(), this.dieOberflaeche.getRedY(), 40, 40);
    }

    @Override
    public boolean isDoubleBuffered() {
	return true;
    }

}

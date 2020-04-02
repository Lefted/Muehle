package me.moritz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class Spielfeld extends JPanel {

    // ATTRIBUTE
    private Oberflaeche dieOberflaeche;
    private Steuerung dieSteuerung;
    private Knotenpunkt[] dieKnotenpunkte = new Knotenpunkt[24];

    // KONSTRUKTOR
    public Spielfeld(Oberflaeche dieOberflaeche) {
	this.dieOberflaeche = dieOberflaeche;
	this.dieSteuerung = dieOberflaeche.getDieSteuerung();

	// Double-Buffering aktivieren
	setDoubleBuffered(true);

	addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		// DEBUG
		System.out.println("x:" + e.getX() + " y:" + e.getY());

		// DEBUG
		// Knotenpunkte zeichnen
		for (Knotenpunkt knotenpunkt : dieKnotenpunkte) {
		    if (knotenpunkt != null) {
			if (knotenpunkt.istMausÜber(e.getX(), e.getY())) {
			    // System.out.println("Knotenpunkt geklickt");
			}
		    }
		}
	    }
	});

	addMouseMotionListener(new MouseAdapter() {
	    @Override
	    public void mouseMoved(MouseEvent e) {
		for (Knotenpunkt knotenpunkt : dieKnotenpunkte) {
		    if (knotenpunkt != null) {
			if (knotenpunkt.istMausÜber(e.getX(), e.getY())) {
			    knotenpunkt.setFarbe(new Color(255, 255, 204, 123));
			} else {
			    knotenpunkt.setFarbe(new Color(1F, 1F, 1F, 0F));
			}
		    }
		}
	    }
	});

	// Knotenpunkte erstellen
	dieKnotenpunkte[0] = new Knotenpunkt(150 - Knotenpunkt.WIDTH / 2, 145 - Knotenpunkt.HEIGHT / 2);
	dieKnotenpunkte[1] = new Knotenpunkt(480 - Knotenpunkt.WIDTH / 2, 145 - Knotenpunkt.HEIGHT / 2);
	dieKnotenpunkte[2] = new Knotenpunkt(815 - Knotenpunkt.WIDTH / 2, 145 - Knotenpunkt.HEIGHT / 2);
	dieKnotenpunkte[3] = new Knotenpunkt(260 - Knotenpunkt.WIDTH / 2, 260 - Knotenpunkt.HEIGHT / 2);
	dieKnotenpunkte[4] = new Knotenpunkt(480 - Knotenpunkt.WIDTH / 2, 260 - Knotenpunkt.HEIGHT / 2);
	dieKnotenpunkte[5] = new Knotenpunkt(700 - Knotenpunkt.WIDTH / 2, 260 - Knotenpunkt.HEIGHT / 2);

	dieKnotenpunkte[6] = new Knotenpunkt(370 - Knotenpunkt.WIDTH / 2, 370 - Knotenpunkt.HEIGHT / 2);
	dieKnotenpunkte[7] = new Knotenpunkt(480 - Knotenpunkt.WIDTH / 2, 370 - Knotenpunkt.HEIGHT / 2);
	dieKnotenpunkte[8] = new Knotenpunkt(590 - Knotenpunkt.WIDTH / 2, 370 - Knotenpunkt.HEIGHT / 2);

	dieKnotenpunkte[9] = new Knotenpunkt(150 - Knotenpunkt.WIDTH / 2, 480 - Knotenpunkt.HEIGHT / 2);
	dieKnotenpunkte[10] = new Knotenpunkt(260 - Knotenpunkt.WIDTH / 2, 480 - Knotenpunkt.HEIGHT / 2);
	dieKnotenpunkte[11] = new Knotenpunkt(370 - Knotenpunkt.WIDTH / 2, 480 - Knotenpunkt.HEIGHT / 2);

	dieKnotenpunkte[12] = new Knotenpunkt(590 - Knotenpunkt.WIDTH / 2, 480 - Knotenpunkt.HEIGHT / 2);
	dieKnotenpunkte[13] = new Knotenpunkt(700 - Knotenpunkt.WIDTH / 2, 480 - Knotenpunkt.HEIGHT / 2);
	dieKnotenpunkte[14] = new Knotenpunkt(815 - Knotenpunkt.WIDTH / 2, 480 - Knotenpunkt.HEIGHT / 2);

	dieKnotenpunkte[15] = new Knotenpunkt(370 - Knotenpunkt.WIDTH / 2, 590 - Knotenpunkt.HEIGHT / 2);
	dieKnotenpunkte[16] = new Knotenpunkt(480 - Knotenpunkt.WIDTH / 2, 590 - Knotenpunkt.HEIGHT / 2);
	dieKnotenpunkte[17] = new Knotenpunkt(590 - Knotenpunkt.WIDTH / 2, 590 - Knotenpunkt.HEIGHT / 2);

	dieKnotenpunkte[18] = new Knotenpunkt(260 - Knotenpunkt.WIDTH / 2, 700 - Knotenpunkt.HEIGHT / 2);
	dieKnotenpunkte[19] = new Knotenpunkt(480 - Knotenpunkt.WIDTH / 2, 700 - Knotenpunkt.HEIGHT / 2);
	dieKnotenpunkte[20] = new Knotenpunkt(700 - Knotenpunkt.WIDTH / 2, 700 - Knotenpunkt.HEIGHT / 2);

	dieKnotenpunkte[21] = new Knotenpunkt(150 - Knotenpunkt.WIDTH / 2, 815 - Knotenpunkt.HEIGHT / 2);
	dieKnotenpunkte[22] = new Knotenpunkt(480 - Knotenpunkt.WIDTH / 2, 815 - Knotenpunkt.HEIGHT / 2);
	dieKnotenpunkte[23] = new Knotenpunkt(815 - Knotenpunkt.WIDTH / 2, 815 - Knotenpunkt.HEIGHT / 2);
    }

    // METHODEN
    @Override
    public Dimension getPreferredSize() {
	return new Dimension(Oberflaeche.PANE_WIDTH, Oberflaeche.PANE_HEIGHT);
    }

    // zeichnen
    @Override
    protected void paintComponent(Graphics g) {
	// Hintergrund des Panels zeichnen
	super.paintComponent(g);

	// Alles vorher Gezeichnete löschen
	g.clearRect(0, 0, Oberflaeche.PANE_WIDTH, Oberflaeche.PANE_HEIGHT);

	// Wenn im Menu nicht zeichnen
	if (dieSteuerung.getSpielzustand() == Spielzustand.MENU) {
	    return;
	}

	g.setColor(new Color(0xFFFFCC));
	g.fillRect(0, 0, 960, 960);

	// Spielfeld Hintergrund zeichnen
	g.drawImage(Utilities.ladeBild("/spielfeld730Pixelart3.png"), 115, 115, this);

	// DEBUG
	// Knotenpunkte zeichnen
	for (Knotenpunkt knotenpunkt : dieKnotenpunkte) {
	    if (knotenpunkt != null) {
		knotenpunkt.zeichnen(g);
	    }
	}
    }
}

package me.moritz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
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
		// System.out.println("x:" + e.getX() + " y:" + e.getY());

		// DEBUG
		// Knotenpunkte zeichnen
		for (Knotenpunkt knotenpunkt : dieKnotenpunkte) {
		    if (knotenpunkt != null) {
			if (knotenpunkt.istMausÜber(e.getX(), e.getY())) {
			    System.out.println("Knotenpunkt geklickt");
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
			    knotenpunkt.setFarbe(Color.BLUE);
			} else {
			    knotenpunkt.setFarbe(Color.RED);
			}
		    }
		}
	    }
	});

	// Knotenpunkte erstellen
	dieKnotenpunkte[0] = new Knotenpunkt(150, 50);
	dieKnotenpunkte[1] = new Knotenpunkt(230 - 12, 290 - 12);
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

	// Spielfeld Hintergrund zeichnen
	
	// für pane 800 600
//	g.drawImage(Utilities.ladeBild("/spielfeld1200.png"), 140, 40, 520, 520, this);
//	g.drawImage(Utilities.ladeBild("/spielfeld810.png"), 0, 0, this);
	g.drawImage(Utilities.ladeBild("/spielfeld730.png"), 40, 40, this);
	

	// DEBUG
	// Knotenpunkte zeichnen
	for (Knotenpunkt knotenpunkt : dieKnotenpunkte) {
	    if (knotenpunkt != null) {
		knotenpunkt.zeichnen(g);
	    }
	}
    }
}

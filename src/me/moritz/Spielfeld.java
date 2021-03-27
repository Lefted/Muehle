package me.moritz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

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
	    public void mousePressed(MouseEvent e) {
		for (Knotenpunkt knotenpunkt : dieKnotenpunkte) {
		    if (knotenpunkt != null) {
			if (knotenpunkt.istMausÜber(e.getX(), e.getY())) {
			    dieSteuerung.knotenpunktGeklickt(knotenpunkt);
			}
		    }
		}
	    }
	});

	// Macht den Knotenpunkt über dem sich die Maus befindet kenntlich
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
	dieKnotenpunkte[0] = new Knotenpunkt(this, 150 - Knotenpunkt.WIDTH / 2, 145 - Knotenpunkt.HEIGHT / 2);
	dieKnotenpunkte[1] = new Knotenpunkt(this, 480 - Knotenpunkt.WIDTH / 2, 145 - Knotenpunkt.HEIGHT / 2);
	dieKnotenpunkte[2] = new Knotenpunkt(this, 815 - Knotenpunkt.WIDTH / 2, 145 - Knotenpunkt.HEIGHT / 2);
	dieKnotenpunkte[3] = new Knotenpunkt(this, 260 - Knotenpunkt.WIDTH / 2, 260 - Knotenpunkt.HEIGHT / 2);
	dieKnotenpunkte[4] = new Knotenpunkt(this, 480 - Knotenpunkt.WIDTH / 2, 260 - Knotenpunkt.HEIGHT / 2);
	dieKnotenpunkte[5] = new Knotenpunkt(this, 700 - Knotenpunkt.WIDTH / 2, 260 - Knotenpunkt.HEIGHT / 2);

	dieKnotenpunkte[6] = new Knotenpunkt(this, 370 - Knotenpunkt.WIDTH / 2, 370 - Knotenpunkt.HEIGHT / 2);
	dieKnotenpunkte[7] = new Knotenpunkt(this, 480 - Knotenpunkt.WIDTH / 2, 370 - Knotenpunkt.HEIGHT / 2);
	dieKnotenpunkte[8] = new Knotenpunkt(this, 590 - Knotenpunkt.WIDTH / 2, 370 - Knotenpunkt.HEIGHT / 2);

	dieKnotenpunkte[9] = new Knotenpunkt(this, 150 - Knotenpunkt.WIDTH / 2, 480 - Knotenpunkt.HEIGHT / 2);
	dieKnotenpunkte[10] = new Knotenpunkt(this, 260 - Knotenpunkt.WIDTH / 2, 480 - Knotenpunkt.HEIGHT / 2);
	dieKnotenpunkte[11] = new Knotenpunkt(this, 370 - Knotenpunkt.WIDTH / 2, 480 - Knotenpunkt.HEIGHT / 2);

	dieKnotenpunkte[12] = new Knotenpunkt(this, 590 - Knotenpunkt.WIDTH / 2, 480 - Knotenpunkt.HEIGHT / 2);
	dieKnotenpunkte[13] = new Knotenpunkt(this, 700 - Knotenpunkt.WIDTH / 2, 480 - Knotenpunkt.HEIGHT / 2);
	dieKnotenpunkte[14] = new Knotenpunkt(this, 815 - Knotenpunkt.WIDTH / 2, 480 - Knotenpunkt.HEIGHT / 2);

	dieKnotenpunkte[15] = new Knotenpunkt(this, 370 - Knotenpunkt.WIDTH / 2, 590 - Knotenpunkt.HEIGHT / 2);
	dieKnotenpunkte[16] = new Knotenpunkt(this, 480 - Knotenpunkt.WIDTH / 2, 590 - Knotenpunkt.HEIGHT / 2);
	dieKnotenpunkte[17] = new Knotenpunkt(this, 590 - Knotenpunkt.WIDTH / 2, 590 - Knotenpunkt.HEIGHT / 2);

	dieKnotenpunkte[18] = new Knotenpunkt(this, 260 - Knotenpunkt.WIDTH / 2, 700 - Knotenpunkt.HEIGHT / 2);
	dieKnotenpunkte[19] = new Knotenpunkt(this, 480 - Knotenpunkt.WIDTH / 2, 700 - Knotenpunkt.HEIGHT / 2);
	dieKnotenpunkte[20] = new Knotenpunkt(this, 700 - Knotenpunkt.WIDTH / 2, 700 - Knotenpunkt.HEIGHT / 2);

	dieKnotenpunkte[21] = new Knotenpunkt(this, 150 - Knotenpunkt.WIDTH / 2, 815 - Knotenpunkt.HEIGHT / 2);
	dieKnotenpunkte[22] = new Knotenpunkt(this, 480 - Knotenpunkt.WIDTH / 2, 815 - Knotenpunkt.HEIGHT / 2);
	dieKnotenpunkte[23] = new Knotenpunkt(this, 815 - Knotenpunkt.WIDTH / 2, 815 - Knotenpunkt.HEIGHT / 2);
    }

    // METHODEN
    @Override
    public Dimension getPreferredSize() {
	return new Dimension(Oberflaeche.PANE_WIDTH, Oberflaeche.PANE_HEIGHT);
    }

    // DEBUG
    private BufferedImage steinSchwarz = Utilities.ladeBild("/SteinSchwarz.png");
    private BufferedImage steinWeiss = Utilities.ladeBild("/SteinWeiß.png");
    private BufferedImage kasten = Utilities.ladeBild("/kasten2.png");

    
    // zeichnen
    @Override
    protected void paintComponent(Graphics g) {
	// Hintergrund des Panels zeichnen
	super.paintComponent(g);

	// Alles vorher Gezeichnete löschen
	g.clearRect(0, 0, Oberflaeche.PANE_WIDTH, Oberflaeche.PANE_HEIGHT);
	
	// Wenn im Menu oder Farbwahl nicht zeichnen
	if (dieSteuerung.getMenuzustand() == Zustand.MENU || dieSteuerung.getMenuzustand() == Zustand.EINSTELLUNGEN) {
	    return;
	}

	g.setColor(new Color(0xFFFFCC));
	g.fillRect(0, 0, 960, 960);

	// Spielfeld Hintergrund zeichnen
	g.drawImage(Utilities.ladeBild("/spielfeld.png"), 115, 115, this);

	// DEBUG
	g.drawImage(kasten, dieOberflaeche.PANE_WIDTH / 2 - 678 / 2- 4, 830, this);
	
	// Maushover zeichnen
	for (Knotenpunkt knotenpunkt : dieKnotenpunkte) {
	    if (knotenpunkt != null) {
		knotenpunkt.zeichnen(g);
	    }
	}
    }
}

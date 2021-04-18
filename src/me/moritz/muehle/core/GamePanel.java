package me.moritz.muehle.core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import me.moritz.muehle.models.Point;
import me.moritz.muehle.utils.ImageUtils;

public class GamePanel extends JPanel implements MouseMotionListener, MouseInputListener {

    private final BufferedImage background;
    private final Color backgroundColor;

    private final Point[] points;

    public GamePanel() {
	background = ImageUtils.INSTANCE.loadImage("background.png");
	backgroundColor = new Color(0xFFFFCC);

	this.points = Controller.INSTANCE.getGameHandler().getPoints();

	addMouseMotionListener(this);
	addMouseListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
	super.paintComponents(g);

	// clear what was drawn before
	g.clearRect(0, 0, Gui.PANE_SIZE, Gui.PANE_SIZE);

	// draw background color
	g.setColor(backgroundColor);
	g.fillRect(0, 0, Gui.PANE_SIZE, Gui.PANE_SIZE);

	// draw the background image
	g.drawImage(background, 55, 45, null);

	// draw the points
	for (Point point : points) {
	    if (point == null)
		continue;

	    point.draw(g);
	}
    }

    @Override
    public void mouseDragged(MouseEvent var1) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
	for (Point point : points) {
	    if (point == null)
		continue;

	    point.onMouseMoved(e.getX(), e.getY());
	}

	repaint();
    }

    @Override
    public void mouseClicked(MouseEvent var1) {
    }

    @Override
    public void mousePressed(MouseEvent var1) {
	if (Controller.INSTANCE.getGameHandler().isGameDone())
	    return;

	boolean clickedVoid = true;

	if (var1.getButton() == MouseEvent.BUTTON1) {
	    for (Point point : points) {
		if (point == null)
		    continue;

		if (!point.isMouseOver(var1.getX(), var1.getY()))
		    continue;

		// delegate mouse click to active player's state
		// this blocks the gui thread but it's ok because the click should be fully processed before other things happen
		Controller.INSTANCE.getGameHandler().getActivePlayer().getCurrentState().onPointClicked(point);
		clickedVoid = false;
		break;
	    }
	}

	if (clickedVoid)
	    Controller.INSTANCE.getGameHandler().getActivePlayer().getCurrentState().onVoidClicked();

	repaint();
    }

    @Override
    public void mouseReleased(MouseEvent var1) {
	repaint();
    }

    @Override
    public void mouseEntered(MouseEvent var1) {
    }

    @Override
    public void mouseExited(MouseEvent var1) {
    }

}
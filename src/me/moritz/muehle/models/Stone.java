package me.moritz.muehle.models;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import me.moritz.muehle.utils.ImageUtils;

public class Stone {

    private static final BufferedImage imgBlackStone = ImageUtils.INSTANCE.loadImage("res/stone_black.png");
    private static final BufferedImage imgWhiteStone = ImageUtils.INSTANCE.loadImage("res/stone_white.png");

    private final Color color;
    private Point point;

    public Stone(Color color) {
	this.color = color;
    }

    public void draw(Graphics g) {
	if (point == null)
	    return;

	if (color == Color.BLACK)
	    g.drawImage(imgBlackStone, point.getX(), point.getY(), null);
	else
	    g.drawImage(imgWhiteStone, point.getX(), point.getY(), null);
    }

    public Color getColor() {
	return color;
    }

    public Point getPoint() {
	return point;
    }

    public void setPoint(Point point) {
	this.point = point;
    }
}

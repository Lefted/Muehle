package me.moritz.muehle.models;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import me.moritz.muehle.exceptions.ResourceLocationException;
import me.moritz.muehle.utils.ImageUtils;

public class Stone {

    private static BufferedImage imgBlackStone;
    private static BufferedImage imgWhiteStone;

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

    public static void loadResources() throws ResourceLocationException {
	imgBlackStone = ImageUtils.INSTANCE.loadImage("stone_black.png");
	imgWhiteStone = ImageUtils.INSTANCE.loadImage("stone_white.png");
    }
}
package me.moritz.muehle.models;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import me.moritz.muehle.core.Controller;
import me.moritz.muehle.utils.ImageUtils;

public class Point {

    // [column][row][circle][0==x;1==y]
    public static final int[][][][] COORDINATES = { { { { 55, 45 }, { 166, 158 }, { 277, 268 } }, { { 55, 378 }, { 166, 378 }, { 277, 378 } }, { { 55, 708 }, {
	    166, 597 }, { 277, 486 } } }, { { { 388, 45 }, { 388, 158 }, { 388, 268 } }, { {}, {}, {} }, { { 388, 708 }, { 388, 597 }, { 388, 486 } } }, { { {
		    720, 45 }, { 608, 158 }, { 498, 268 } }, { { 720, 378 }, { 608, 378 }, { 498, 378 } }, { { 720, 708 }, { 608, 597 }, { 498, 486 } } } };

    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;

    // used to determine whether points are neighbours
    final private int column;
    final private int row;
    final private int circle;

    // actual coordinates
    final private int x;
    final private int y;

    private boolean highlighted;
    final private Color highlightColor;
    final private Color nonHighlightColor;
    private final BufferedImage selectionImg;

    private Stone stone;

    public Point(int column, int row, int circle) {
	this.column = column;
	this.row = row;
	this.circle = circle;

	x = COORDINATES[column][row][circle][0];
	y = COORDINATES[column][row][circle][1];

	highlightColor = new Color(255, 255, 204, 123);
	nonHighlightColor = new Color(255, 255, 255, 0);

	selectionImg = ImageUtils.INSTANCE.loadImage("res/selection.png");
    }

    public void placeStone(me.moritz.muehle.models.Color color) {
	stone = new Stone(color);
	stone.setPoint(this);
    }

    public void onMouseMoved(int mouseX, int mouseY) {
	highlighted = isMouseOver(mouseX, mouseY);
    }

    public boolean isMouseOver(int mouseX, int mouseY) {

	final boolean flag1 = (mouseX) > x && (mouseX < (x + WIDTH));
	final boolean flag2 = (mouseY) > y && (mouseY < (y + HEIGHT));
	return flag1 && flag2;
    }

    public void draw(Graphics g) {
	// draw stone
	if (stone != null)
	    stone.draw(g);

	// draw highlighting
	final Color overlayColor = highlighted ? highlightColor : nonHighlightColor;
	g.setColor(overlayColor);
	g.fillRect(x, y, WIDTH, HEIGHT);

	// draw selection
	if (stone != null && Controller.INSTANCE.getActivePlayer().getSelectedPoint() == this)
	    g.drawImage(selectionImg, x - 8, y - 8, null);

    }

    public int getX() {
	return x;
    }

    public int getY() {
	return y;
    }

    public int getColumn() {
	return column;
    }

    public int getRow() {
	return row;
    }

    public int getCircle() {
	return circle;
    }

    public Stone getStone() {
	return stone;
    }

    public void setStone(Stone stone) {
	this.stone = stone;
    }
}

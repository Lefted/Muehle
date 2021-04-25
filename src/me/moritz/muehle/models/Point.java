package me.moritz.muehle.models;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import me.moritz.muehle.core.Controller;
import me.moritz.muehle.exceptions.ResourceLocationException;
import me.moritz.muehle.utils.ImageUtils;

public class Point {

    private static BufferedImage selectionImage;

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

    private Stone stone;

    public Point(int column, int row, int circle) {
	this.column = column;
	this.row = row;
	this.circle = circle;

	x = COORDINATES[column][row][circle][0];
	y = COORDINATES[column][row][circle][1];

	highlightColor = new Color(255, 255, 204, 123);
	nonHighlightColor = new Color(255, 255, 255, 0);
    }

    public static void loadResources() throws ResourceLocationException {
	selectionImage = ImageUtils.INSTANCE.loadImage("selection.png");
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
	if (stone != null && Controller.INSTANCE.getGameHandler().getActivePlayer().getSelectedPoint() == this)
	    g.drawImage(selectionImage, x - 8, y - 8, null);
    }

    public boolean isNeighbourTo(Point point) {
	final int deltaColumn = Math.abs(point.column - this.column);
	final int deltaRow = Math.abs(point.row - this.row);
	final int deltaCircle = Math.abs(point.circle - this.circle);

	if (deltaCircle == 0) {
	    if (deltaColumn == 1 && deltaRow == 0)
		return true;
	    if (deltaRow == 1 && deltaColumn == 0)
		return true;
	}

	if (this.row == 1 && deltaCircle == 1 && deltaColumn == 0 && deltaRow == 0)
	    return true;

	if (this.column == 1 && deltaCircle == 1 && deltaColumn == 0 && deltaRow == 0)
	    return true;

	return false;

    }

    public boolean isInMill() {

	if (stone == null)
	    return false;

	// check column
	if (column != 1) {
	    boolean isMillColumn = true;

	    for (int r = 0; r < 3; r++) {

		if (Controller.INSTANCE.getGameHandler().getPointAt(column, r, circle).getStone() == null)
		    isMillColumn = false;
		else if (Controller.INSTANCE.getGameHandler().getPointAt(column, r, circle).getStone().getColor() != stone.getColor())
		    isMillColumn = false;
	    }

	    if (isMillColumn)
		return true;
	}

	if (column == 1) {
	    boolean isMillColumn = true;

	    for (int circ = 0; circ < 3; circ++) {

		if (Controller.INSTANCE.getGameHandler().getPointAt(column, row, circ).getStone() == null)
		    isMillColumn = false;
		else if (Controller.INSTANCE.getGameHandler().getPointAt(column, row, circ).getStone().getColor() != stone.getColor())
		    isMillColumn = false;
	    }

	    if (isMillColumn)
		return true;
	}

	// check row
	if (row != 1) {

	    boolean isMillRow = true;

	    for (int c = 0; c < 3; c++) {

		if (Controller.INSTANCE.getGameHandler().getPointAt(c, row, circle).getStone() == null)
		    isMillRow = false;
		else if (Controller.INSTANCE.getGameHandler().getPointAt(c, row, circle).getStone().getColor() != stone.getColor())
		    isMillRow = false;
	    }

	    if (isMillRow)
		return true;
	}

	if (row == 1) {
	    boolean isMillRow = true;

	    for (int circ = 0; circ < 3; circ++) {

		if (Controller.INSTANCE.getGameHandler().getPointAt(column, row, circ).getStone() == null)
		    isMillRow = false;
		else if (Controller.INSTANCE.getGameHandler().getPointAt(column, row, circ).getStone().getColor() != stone.getColor()) {
		    isMillRow = false;
		}
	    }

	    if (isMillRow)
		return true;
	}
	return false;
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

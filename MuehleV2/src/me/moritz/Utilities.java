package me.moritz;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Utilities {

    // lädt ein Bild
    public static BufferedImage ladeBild(String path) {
	try {
	    return ImageIO.read(Utilities.class.getResource(path));
	} catch (IOException e) {
	    e.printStackTrace();
	    System.exit(1);
	}
	return null;
    }

    public static BufferedImage rotate(BufferedImage image, double angle) {
	double sin = Math.abs(Math.sin(angle)), cos = Math.abs(Math.cos(angle));
	int w = image.getWidth(), h = image.getHeight();
	int neww = (int) Math.floor(w * cos + h * sin), newh = (int) Math.floor(h * cos + w * sin);
	GraphicsConfiguration gc = getDefaultConfiguration();
	BufferedImage result = gc.createCompatibleImage(neww, newh, Transparency.TRANSLUCENT);
	Graphics2D g = result.createGraphics();
	g.translate((neww - w) / 2, (newh - h) / 2);
	g.rotate(angle, w / 2, h / 2);
	g.drawRenderedImage(image, null);
	g.dispose();
	return result;
    }

    private static GraphicsConfiguration getDefaultConfiguration() {
	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	GraphicsDevice gd = ge.getDefaultScreenDevice();
	return gd.getDefaultConfiguration();
    }

}

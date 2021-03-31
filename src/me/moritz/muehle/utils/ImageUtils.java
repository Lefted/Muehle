package me.moritz.muehle.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtils {

    public static final ImageUtils INSTANCE = new ImageUtils();

    public BufferedImage loadImage(String path) {
	try {
	    final File file = new File(path);
	    return ImageIO.read(file);
	} catch (IOException e) {
	    e.printStackTrace();
	    System.err.println(String.format("Unable to load image from path %s", path));
	    System.exit(1);
	}
	return null;
    }
}

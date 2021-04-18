package me.moritz.muehle.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ImageUtils {

    public static final ImageUtils INSTANCE = new ImageUtils();

    public BufferedImage loadImage(String path) {
	try {
	    // final File file = new File(ClassLoader.getSystemResource(path).toURI());
	    final InputStream input = ClassLoader.getSystemResourceAsStream(path);
	    return ImageIO.read(input);
	} catch (IOException e) {
	    e.printStackTrace();
	    System.err.println(String.format("Unable to load image from path %s", path));
	    System.exit(1);
	}
	return null;
    }
}

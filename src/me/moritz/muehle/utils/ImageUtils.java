package me.moritz.muehle.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;

import me.moritz.muehle.exceptions.ResourceLocationException;

public class ImageUtils {

    public static final ImageUtils INSTANCE = new ImageUtils();

    public BufferedImage loadImage(String path) throws ResourceLocationException {
	try {
	    final File file = new File(ClassLoader.getSystemResource(path).toURI());
	    final InputStream input = ClassLoader.getSystemResourceAsStream(path);
	    return ImageIO.read(input);
	} catch (IOException | URISyntaxException e) {
	    System.err.println(String.format("Unable to load image from path %s", path));
	    throw new ResourceLocationException(e, path);
	}
    }
}
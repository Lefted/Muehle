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
	    final InputStream input = ClassLoader.getSystemResourceAsStream(path);
	    return ImageIO.read(input);
	} catch (IOException e) {
	    System.err.println(String.format("Unable to load image from path %s", path));
	    throw new ResourceLocationException(e, path);
	}
    }
}
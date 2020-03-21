package me.moritz;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;

import javax.imageio.ImageIO;

public class Utilities {

    // lädt ein Bild
    public static BufferedImage ladeBild(String path) {
	try {
	    
//	    System.out.println(Utilities.class.getResource(path));
//	    Utilities.class.getClassLoader().getre
	    return ImageIO.read(Utilities.class.getResource(path));
	} catch (IOException e) {
	    e.printStackTrace();
	    System.exit(1);
	}
	return null;
    }

}

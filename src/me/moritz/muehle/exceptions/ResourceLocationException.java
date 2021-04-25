package me.moritz.muehle.exceptions;

public class ResourceLocationException extends Exception {

    final String path;

    public ResourceLocationException(Throwable superException, String path) {
	super(superException);
	this.path = path;
    }

    public String getPath() {
	return path;
    }

    @Override
    public String getMessage() {
	return String.format("Unable to load image from path %s!", path);
    }
}
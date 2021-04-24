package me.moritz.muehle.arguments;

import me.moritz.muehle.models.Color;

public class LocalMultiplayerGameArguments extends GameArguments{

    private final Color firstPlayerColor;
    private final Color secondPlayerColor;

    public LocalMultiplayerGameArguments(Color firstPlayerColor, Color secondPlayerColor) {
	super(false);
	
	this.firstPlayerColor = firstPlayerColor;
	this.secondPlayerColor = secondPlayerColor;
    }

    public Color getFirstPlayerColor() {
	return firstPlayerColor;
    }

    public Color getSecondPlayerColor() {
	return secondPlayerColor;
    }
}

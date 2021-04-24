package me.moritz.muehle.settings;

import me.moritz.muehle.models.Color;

public class LocalMultiplayerGameSettings extends GameSettings {

    private final Color firstPlayerColor;
    private final Color secondPlayerColor;

    public LocalMultiplayerGameSettings(Color firstPlayerColor, Color secondPlayerColor) {
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

package me.moritz.muehle.settings;

public abstract class GameSettings {

    private final boolean online;

    public GameSettings(boolean online) {
	super();
	this.online = online;
    }

    public boolean isOnline() {
	return online;
    }
}
package me.moritz.muehle.models;

public enum Color {

    BLACK, WHITE;

    @Override
    public String toString() {
	return this == BLACK ? "Black" : "White";

    }

}

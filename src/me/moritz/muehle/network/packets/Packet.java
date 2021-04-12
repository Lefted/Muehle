package me.moritz.muehle.network.packets;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.prefs.AbstractPreferences;

public abstract class Packet implements Serializable {

    private static final long serialVersionUID = 2021_04_11_1345L;

    private final int typeId;

    public Packet(int typeId) {
	this.typeId = typeId;
    }

    public int getTypeId() {
	return typeId;
    }
}

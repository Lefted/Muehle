package me.moritz.muehle.multiplayer.network.packets;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.prefs.AbstractPreferences;

public abstract class Packet implements Serializable {

    private static final long serialVersionUID = 2021_04_10_1834L;

    private final int typeId;
    private final String payload;

    private static Map<Class, Integer> classTypeMap;

    public Packet(int packetId, String payload) {
	this.typeId = packetId;
	this.payload = payload;

	if (classTypeMap == null)
	    classTypeMap = new HashMap<>();

	classTypeMap.put(getClass(), typeId);
    }

    public static Class getPacketClassbyTypeId(int typeId) {
	if (!classTypeMap.values().contains(typeId))
	    return null;
	
	return classTypeMap.entrySet().stream().filter((entry) -> entry.getValue() == typeId).findFirst().get().getKey();
    }

    public int getTypeId() {
	return typeId;
    }

    public String getPayload() {
	return payload;
    }
}

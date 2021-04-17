package me.moritz.muehle.network.packets;

public class TestPacket extends Packet {

    public static final int TYPE_ID = 0;

    private String testMsg;

    public TestPacket(String testmsg) {
	super(TYPE_ID);
	this.testMsg = testmsg;
    }

    public String getTestMsg() {
	return testMsg;
    }

    @Override
    public void handle() {
    }
}

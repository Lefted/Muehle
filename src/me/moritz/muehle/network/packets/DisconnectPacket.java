package me.moritz.muehle.network.packets;

import javax.swing.JOptionPane;

import me.moritz.muehle.core.Controller;
import me.moritz.muehle.core.gamehandler.GameHandler;
import me.moritz.muehle.core.gamehandler.OnlineMultiplayerGameHandler;
import me.moritz.muehle.network.NetworkHandler;

public class DisconnectPacket extends Packet {

    public static final int TYPE_ID = 7;

    public DisconnectPacket() {
	super(TYPE_ID);
    }

    @Override
    public void handle() {
	final GameHandler gameHandler = Controller.INSTANCE.getGameHandler();

	if (!(gameHandler instanceof OnlineMultiplayerGameHandler))
	    return;

	// close own connection
	final NetworkHandler networkHandler = ((OnlineMultiplayerGameHandler) gameHandler).getNetworkHandler();
	networkHandler.closeConnection();

	// show message
	JOptionPane.showMessageDialog(Controller.INSTANCE.getGui(), "The other player has disconnected!");
    }

}

package me.moritz.muehle.network.packets;

import javax.swing.JOptionPane;

import me.moritz.muehle.core.Controller;
import me.moritz.muehle.core.gamehandler.GameHandler;
import me.moritz.muehle.models.Player;

public class WinPacket extends Packet {

    public static final int TYPE_ID = 5;

    private final boolean suffocated;

    public WinPacket(boolean suffocated) {
	super(TYPE_ID);

	this.suffocated = suffocated;
    }

    @Override
    public void handle() {
	final GameHandler handler = Controller.INSTANCE.getGameHandler();
	final Player activePlayer = handler.getActivePlayer();

	final String reason_phrase = suffocated ? "(Suffocated)" : "(less than three stones)";

	Controller.INSTANCE.getGui().setStatus(String.format("%s has won the game! You lost %s", activePlayer.getColor().toString(), reason_phrase));
	JOptionPane.showMessageDialog(Controller.INSTANCE.getGui(), String.format("%s has won the game! %s", activePlayer.getColor().toString(),
	    reason_phrase));
	Controller.INSTANCE.getGameHandler().setGameDone(true);
    }

}

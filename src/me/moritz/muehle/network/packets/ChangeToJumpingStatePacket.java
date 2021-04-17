package me.moritz.muehle.network.packets;

import javax.swing.JOptionPane;

import me.moritz.muehle.core.Controller;
import me.moritz.muehle.models.Player;
import me.moritz.muehle.states.playerstates.PlayerStates;

public class ChangeToJumpingStatePacket extends Packet {

    public static final int TYPE_ID = 6;

    public ChangeToJumpingStatePacket() {
	super(TYPE_ID);
    }

    @Override
    public void handle() {
	// opponentPlayer which is the local player
	final Player opponentPlayer = Controller.INSTANCE.getGameHandler().getOpponentPlayer();
	opponentPlayer.setCurrentState(PlayerStates.JUMP_STATE);

	JOptionPane.showMessageDialog(Controller.INSTANCE.getGui(), "You have only 3 stones left. You can now jump!");
    }

}

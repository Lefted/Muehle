package me.moritz.muehle.states.playerstates;

import me.moritz.muehle.core.Controller;
import me.moritz.muehle.models.Point;
import me.moritz.muehle.network.packets.Packet;

public class WaitState implements PlayerState {

    @Override
    public void onPointClicked(Point point) {
    }

    @Override
    public void onVoidClicked() {
    }

    @Override
    public void refreshStatus() {
	Controller.INSTANCE.getGui().setStatus("The other player is making a move..");
    }
    
    public void onPacketRecieved(Packet packet) {
	
    }
    
    private void moveDone() {
	Controller.INSTANCE.getGameHandler().changePlayers();
    }

}
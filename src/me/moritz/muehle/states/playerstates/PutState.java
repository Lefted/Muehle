package me.moritz.muehle.states.playerstates;

import javax.swing.JOptionPane;

import me.moritz.muehle.core.Controller;
import me.moritz.muehle.core.gamehandler.GameHandler;
import me.moritz.muehle.core.gamehandler.MultiplayerGameHandler;
import me.moritz.muehle.core.gamehandler.SingleplayerGameHandler;
import me.moritz.muehle.models.Player;
import me.moritz.muehle.models.Point;
import me.moritz.muehle.network.NetworkHandler;
import me.moritz.muehle.network.packets.PutPacket;

public class PutState implements PlayerState {

    @Override
    public void onPointClicked(Point point) {
	final Player activePlayer = Controller.INSTANCE.getGameHandler().getActivePlayer();

	if (point.getStone() == null) {
	    final boolean createdMill = putStoneAndCheckForMill(point);

	    // send packet if multiplayer
	    trySendingPacket(point);

	    if (activePlayer.getStonesPut() == 9) {
		activePlayer.setCurrentState(PlayerStates.MOVE_STATE);
	    }

	    if (createdMill) {

		final boolean canTakeStone = TakeState.checkCanTakeStone();
		if (canTakeStone) {
		    // take a stone
		    final PlayerState nextState = activePlayer.getCurrentState();
		    activePlayer.setCurrentState(new TakeState(nextState));
		} else {
		    // ensure that the last stone is rendered
		    Controller.INSTANCE.getGui().repaintGamePanel();

		    JOptionPane.showMessageDialog(Controller.INSTANCE.getGui(), String.format("%s cannot take a stone from %s", activePlayer.getColor()
			.toString(), Controller.INSTANCE.getGameHandler().getOpponentPlayer().getColor().toString()));
		    Controller.INSTANCE.getGameHandler().changePlayers();
		}
	    } else {
		Controller.INSTANCE.getGameHandler().changePlayers();
	    }
	}
    }

    private boolean putStoneAndCheckForMill(Point point) {
	final Player activePlayer = Controller.INSTANCE.getGameHandler().getActivePlayer();

	point.placeStone(activePlayer.getColor());
	activePlayer.increaseStonesPut();
	activePlayer.increaseStonesLeft();

	if (point.isInMill())
	    return true;

	return false;
    }

    private void trySendingPacket(Point point) {
	final GameHandler handler = Controller.INSTANCE.getGameHandler();

	if (handler instanceof SingleplayerGameHandler)
	    return;

	final MultiplayerGameHandler multiplayerHandler = ((MultiplayerGameHandler) handler);
	final NetworkHandler networkHandler = multiplayerHandler.getNetworkHandler();

	networkHandler.sendPacket(new PutPacket(point.getColumn(), point.getRow(), point.getCircle()));
    }

    @Override
    public void onVoidClicked() {
    }

    @Override
    public void refreshStatus() {
	final Player activePlayer = Controller.INSTANCE.getGameHandler().getActivePlayer();
	final int stonesLeftToPut = 9 - activePlayer.getStonesPut();
	Controller.INSTANCE.getGui().setStatus(String.format("%s must place a stone (%s stones left)", activePlayer.getColor().toString(), stonesLeftToPut));
    }

}

package me.moritz.muehle.core.gamehandler;

import java.util.stream.IntStream;

import me.moritz.muehle.arguments.GameArguments;
import me.moritz.muehle.core.Controller;
import me.moritz.muehle.models.Color;
import me.moritz.muehle.models.Player;
import me.moritz.muehle.network.ClientNetworkHandler;
import me.moritz.muehle.network.NetworkHandler;
import me.moritz.muehle.network.ServerNetworkHandler;
import me.moritz.muehle.states.playerstates.PlayerStates;

public class MultiplayerGameHandler extends GameHandler {

    private NetworkHandler networkHandler;

    @Override
    public void setupGame() {
	final GameArguments args = Controller.INSTANCE.getGameArguments();

	networkHandler = Controller.INSTANCE.getGameArguments().isServer() ? new ServerNetworkHandler(args.getIp(), args.getPort())
	    : new ClientNetworkHandler(args.getIp(), args.getPort());

	createPoints();

	// create players
	players = new Player[2];
	players[args.getOwnPlayerIndex()] = new Player(args.getOwnColor());
	players[args.getOpponentPlayerIndex()] = new Player(args.getOpponentColor());
    }

    @Override
    public void initNewGame() {
	final GameArguments args = Controller.INSTANCE.getGameArguments();

	gameDone = false;
	removeStonesFromField();
	
	// set the active player
	activePlayerIdx = args.getOwnPlayerIndex();
	
	// put second player into wait state
	players[1].setCurrentState(PlayerStates.WAIT_STATE);
	// put first player into put state
	players[0].setCurrentState(PlayerStates.PUT_STATE);
    }
}
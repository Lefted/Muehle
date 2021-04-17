package me.moritz.muehle.core.gamehandler;

import java.util.stream.IntStream;

import me.moritz.muehle.arguments.GameArguments;
import me.moritz.muehle.core.Controller;
import me.moritz.muehle.models.Color;
import me.moritz.muehle.models.Player;
import me.moritz.muehle.network.ClientNetworkHandler;
import me.moritz.muehle.network.NetworkHandler;
import me.moritz.muehle.network.ServerNetworkHandler;
import me.moritz.muehle.network.packets.ChangePlayerPacket;
import me.moritz.muehle.states.playerstates.PlayerStates;

public class MultiplayerGameHandler extends GameHandler {

    private NetworkHandler networkHandler;

    @Override
    public void setupGame() {
	final GameArguments args = Controller.INSTANCE.getGameArguments();

	createPoints();

	// create players
	players = new Player[2];
	players[args.getOwnPlayerIndex()] = new Player(args.getOwnColor());
	players[args.getOpponentPlayerIndex()] = new Player(args.getOpponentColor());

	networkHandler = Controller.INSTANCE.getGameArguments().isServer() ? new ServerNetworkHandler(args.getIp(), args.getPort())
	    : new ClientNetworkHandler(args.getIp(), args.getPort());

	// start network therad
	networkHandler.startThread();
    }

    @Override
    public void initNewGame() {
	final GameArguments args = Controller.INSTANCE.getGameArguments();

	gameDone = false;
	removeStonesFromField();

	// put own player into put state
	players[args.getOwnPlayerIndex()].setCurrentState(PlayerStates.PUT_STATE);
	
	// put other player into recieve packets state
	players[args.getOpponentPlayerIndex()].setCurrentState(PlayerStates.RECIEVE_PACKETS_STATE);
	
	activePlayerIdx = 0;
    }

    public NetworkHandler getNetworkHandler() {
	return networkHandler;
    }

    @Override
    public void changePlayers() {
	super.changePlayers();

	// send packet
	networkHandler.sendPacket(new ChangePlayerPacket());
    }
    
    public void changePlayersWithoutSendingPacket() {
	activePlayerIdx = activePlayerIdx == 0 ? 1 : 0;
	getActivePlayer().getCurrentState().refreshStatus();
    }
}
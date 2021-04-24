package me.moritz.muehle.core.gamehandler;

import me.moritz.muehle.core.Controller;
import me.moritz.muehle.models.Player;
import me.moritz.muehle.network.ClientNetworkHandler;
import me.moritz.muehle.network.NetworkHandler;
import me.moritz.muehle.network.ServerNetworkHandler;
import me.moritz.muehle.network.packets.ChangePlayerPacket;
import me.moritz.muehle.settings.OnlineMultiplayerGameSettings;
import me.moritz.muehle.states.playerstates.PlayerStates;

public class OnlineMultiplayerGameHandler extends GameHandler {

    private NetworkHandler networkHandler;

    @Override
    public void setupGame() {
	final OnlineMultiplayerGameSettings args = (OnlineMultiplayerGameSettings) Controller.INSTANCE.getGameSettings();

	createPoints();

	networkHandler = args.isServer() ? new ServerNetworkHandler(args.getIp(), args.getPort()) : new ClientNetworkHandler(args.getIp(), args.getPort());

	// start network therad
	networkHandler.startThread();

	// try disconnecting on shutdown
	Runtime.getRuntime().addShutdownHook(new Thread(() -> networkHandler.disconnect(), "Shutdown Hook"));
    }

    @Override
    public void setupNewRound() {
	final OnlineMultiplayerGameSettings args = (OnlineMultiplayerGameSettings) Controller.INSTANCE.getGameSettings();

	gameDone = false;
	removeStonesFromField();

	// create players
	players = new Player[2];

	// players[0] own player players[1] other player
	players[0] = new Player(args.getOwnPlayerColor());
	players[1] = new Player(args.getOtherPlayerColor());

	// determine the active player
	final boolean ownPlayerStarts = args.getFirstMoverColor() == args.getOwnPlayerColor();
	activePlayerIdx = ownPlayerStarts ? 0 : 1;

	// own player instance starts in put state
	players[0].setCurrentState(PlayerStates.PUT_STATE);
	// other player instance always listens for packets
	players[1].setCurrentState(PlayerStates.RECIEVE_PACKETS_STATE);
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

    @Override
    public boolean shouldSetupNewRound() {
	final OnlineMultiplayerGameSettings args = (OnlineMultiplayerGameSettings) Controller.INSTANCE.getGameSettings();
	// if information needed is already known (server) then a new round a be setup
	return args.isHasGameArgs();
    }
}
package me.moritz.muehle.core.gamehandler;

import java.util.stream.IntStream;

import me.moritz.muehle.arguments.LocalMultiplayerGameArguments;
import me.moritz.muehle.core.Controller;
import me.moritz.muehle.models.Color;
import me.moritz.muehle.models.Player;
import me.moritz.muehle.states.playerstates.PlayerStates;

// TODO rename to LocalMultiplayerGameHandler
public class SingleplayerGameHandler extends GameHandler {

    @Override
    public void setupGame() {
	createPoints();

	// create players
	players = new Player[2];

	final LocalMultiplayerGameArguments args = (LocalMultiplayerGameArguments) Controller.INSTANCE.getGameArguments();
	final Color firstPlayerColor = args.getFirstPlayerColor();
	final Color secondPlayerColor = args.getSecondPlayerColor();

	players[0] = new Player(firstPlayerColor);
	players[1] = new Player(secondPlayerColor);
    }
    
    @Override
    public void setupNewRound() {
	gameDone = false;
	removeStonesFromField();
	// put both players into PUT_STATE
	IntStream.range(0, 2).forEach((i) -> players[i].setCurrentState(PlayerStates.PUT_STATE));
    }

    @Override
    public boolean shouldSetupNewRound() {
	return true;
    }
}
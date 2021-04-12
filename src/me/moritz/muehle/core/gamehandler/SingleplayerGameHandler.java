package me.moritz.muehle.core.gamehandler;

import java.util.stream.IntStream;

import me.moritz.muehle.models.Color;
import me.moritz.muehle.models.Player;
import me.moritz.muehle.states.playerstates.PlayerStates;

public class SingleplayerGameHandler extends GameHandler {

    @Override
    public void setupGame() {
	createPoints();

	// create players
	players = new Player[2];
	players[0] = new Player(Color.WHITE);
	players[1] = new Player(Color.BLACK);
    }

    @Override
    public void initNewGame() {
	gameDone = false;
	removeStonesFromField();
	// put both players into PUT_STATE
	IntStream.range(0, 2).forEach((i) -> players[i].setCurrentState(PlayerStates.PUT_STATE));
    }

}

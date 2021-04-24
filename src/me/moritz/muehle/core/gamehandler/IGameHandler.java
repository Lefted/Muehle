package me.moritz.muehle.core.gamehandler;

public interface IGameHandler {

    void setupGame();

    void setupNewRound();

    boolean shouldSetupNewRound();
}

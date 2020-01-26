package tennis.service;

import tennis.model.Game;

public interface IGameService {

    Game createGame();
    void play(Game game,int serviceWinner);
    void updateScore(Game game);
    void resetPlayersScore(Game game);
}

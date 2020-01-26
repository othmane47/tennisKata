package tennis.service;

import tennis.model.Game;
import tennis.model.scoreFactory.Score;

public interface IGameService {

    Game createGame();
    void play(Game game,int serviceWinner);
    void resetPlayersScore(Game game);
    Score getGameScore(Game game);
}

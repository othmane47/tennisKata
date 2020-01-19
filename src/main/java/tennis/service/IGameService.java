package tennis.service;

import tennis.model.Game;

public interface IGameService {

    Game createGame(String player1Name,String player2Name);
    void play(Game game,int serviceWinner);
    void updateScore(Game game);
    void resetPlayersScore(Game game);
}

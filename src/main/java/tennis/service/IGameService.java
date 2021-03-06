package tennis.service;

import tennis.exception.GameOverException;
import tennis.exception.NotAllowedException;
import tennis.model.Game;
import tennis.model.factory.Score;

/**
 * The interface Game service.
 */
public interface IGameService {

    /**
     * Create game game.
     *
     * @return the game
     */
    Game createGame() throws NotAllowedException;

    /**
     * Play.
     *
     * @param game          the game
     * @param serviceWinner the service winner
     * @throws GameOverException the game over exception
     */
    void play(Game game,int serviceWinner) throws GameOverException, NotAllowedException;

    /**
     * Reset players score.
     *
     * @param game the game
     */
    void resetPlayersScore(Game game) throws NotAllowedException;

    /**
     * Gets game score.
     *
     * @param game the game
     * @return the game score
     */
    Score getGameScore(Game game);

    /**
     * Add score to game.
     *
     * @param game         the game
     * @param scorePlayer1 the score player 1
     * @param scorePlayer2 the score player 2
     */
    void addScoreToGame(Game game,String scorePlayer1,String scorePlayer2) throws NotAllowedException;

    /**
     * Print score.
     *
     * @param game the game
     */
    void printScore(Game game);
}

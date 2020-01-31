package tennis.service;

import tennis.exception.GameOverException;
import tennis.exception.NotAllowedException;
import tennis.model.Game;
import tennis.model.Set;
import tennis.model.factory.SetScore;
import tennis.model.factory.TiebreakScore;

/**
 * The interface Set service.
 */
public interface ISetService {

    /**
     * Create set.
     *
     * @return the set
     */
    Set createSet() throws NotAllowedException;

    /**
     * Play game.
     *
     * @param set  the set
     * @param game the game
     * @throws GameOverException   the game over exception
     * @throws NotAllowedException the no tie break exception
     */
    void playGame (Set set,Game game) throws GameOverException, NotAllowedException;

    /**
     * Gets set score.
     *
     * @param set the set
     * @return the set score
     */
    SetScore getSetScore(Set set);

    /**
     * Gets tie score.
     *
     * @param set the set
     * @return the tie score
     * @throws NotAllowedException the no tie break exception
     */
    TiebreakScore getTieScore(Set set) throws NotAllowedException;

    /**
     * Add score to set.
     *
     * @param set          the set
     * @param scorePlayer1 the score player 1
     * @param scorePlayer2 the score player 2
     */
    void addScoreToSet(Set set,int scorePlayer1, int scorePlayer2) throws NotAllowedException;

    /**
     * Add score to tiebreak.
     *
     * @param set          the set
     * @param scorePlayer1 the score player 1
     * @param scorePlayer2 the score player 2
     */
    void addScoreToTiebreak(Set set,int scorePlayer1,int scorePlayer2) throws NotAllowedException;

    /**
     * Print score.
     *
     * @param set the set
     * @throws NotAllowedException the no tie break exception
     */
    void printScore(Set set) throws NotAllowedException;


}

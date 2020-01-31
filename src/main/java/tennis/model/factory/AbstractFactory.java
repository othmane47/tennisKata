package tennis.model.factory;

import tennis.exception.NotAllowedException;

/**
 * The interface Abstract factory.
 *
 * @param <T> the type parameter
 */
public interface AbstractFactory<T> {
    /**
     * Create t.
     *
     * @param scoreType    the score type
     * @param scorePlayer1 the score player 1
     * @param scorePlayer2 the score player 2
     * @return the t
     */
    T create (String scoreType,int scorePlayer1,int scorePlayer2) throws NotAllowedException;

    /**
     * Create t.
     *
     * @param scoreType    the score type
     * @param scorePlayer1 the score player 1
     * @param scorePlayer2 the score player 2
     * @return the t
     */
    T create (String scoreType,String scorePlayer1,String scorePlayer2) throws NotAllowedException;
}

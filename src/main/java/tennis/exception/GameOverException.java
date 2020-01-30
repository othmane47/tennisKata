package tennis.exception;

/**
 * The type Game over exception.
 */
public class GameOverException extends Exception {
    /**
     * Instantiates a new Game over exception.
     *
     * @param errorMessage the error message
     */
    public GameOverException(String errorMessage){
        super(errorMessage);
    }

}

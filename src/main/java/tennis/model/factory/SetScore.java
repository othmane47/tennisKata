package tennis.model.factory;

import lombok.Builder;
import lombok.Data;
import lombok.extern.java.Log;

/**
 * The type Set score.
 */
@Data
@Builder
public class SetScore implements Score{

    private int scorePlayer1;
    private int scorePlayer2;
    @Override
    public StringBuilder trace() {
        return new StringBuilder("Set :"+scorePlayer1+"|"+scorePlayer2);

    }
}

package tennis.model.factory;

import lombok.Builder;
import lombok.Data;

/**
 * The type Tiebreak score.
 */
@Data
@Builder
public class TiebreakScore implements Score {

    private int scorePlayer1;
    private int scorePlayer2;

    @Override
    public StringBuilder trace() {
        return new StringBuilder(" Tie-Break :"+scorePlayer1+"|"+scorePlayer2);
    }
}

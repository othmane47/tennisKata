package tennis.model.factory;

import lombok.Builder;
import lombok.Data;
import java.util.Arrays;
import java.util.List;

/**
 * The type Game score.
 */
@Data
@Builder
public class GameScore implements Score {
    /**
     * The constant scores.
     */
    public static final List<String> scores = Arrays.asList("0", "15", "30", "40","ADV","DEUCE");
    private String scorePlayer1;
    private String scorePlayer2;
    @Override
    public StringBuilder trace() {
        return new StringBuilder("Game :"+scorePlayer1+"|"+scorePlayer2);
    }
}

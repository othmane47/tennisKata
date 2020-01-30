package tennis.model;

import lombok.Builder;
import lombok.Data;
import tennis.model.factory.SetScore;
import tennis.model.factory.TiebreakScore;

import java.util.List;

/**
 * The type Set.
 */
@Data
@Builder
public class Set {
    private List<Game> games;
    private Integer setWinner;
    private List<SetScore> setScore;
    private List<TiebreakScore> tieScore;


}

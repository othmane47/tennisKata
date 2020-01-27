package tennis.model;

import lombok.Builder;
import lombok.Data;
import tennis.model.scoreFactory.SetScore;
import tennis.model.scoreFactory.TiebreakScore;

import java.util.List;

@Data
@Builder
public class Set {
    private List<Game> games;
    public Integer setWinner;
    private List<SetScore> setScore;
    private List<TiebreakScore> tieScore;


}

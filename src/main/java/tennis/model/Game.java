package tennis.model;

import lombok.Builder;
import lombok.Data;
import tennis.model.scoreFactory.Score;

import java.util.*;

@Data
@Builder
public class Game {
    private Integer gameWinner;
    private List<Score> gameScore;
}

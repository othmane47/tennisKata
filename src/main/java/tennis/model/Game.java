package tennis.model;

import lombok.Builder;
import lombok.Data;
import tennis.model.factory.GameScore;

import java.util.*;

/**
 * The type Game.
 */
@Data
@Builder
public class Game {
    private Integer gameWinner;
    private List<GameScore> gameScore;
}

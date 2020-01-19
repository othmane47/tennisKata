package tennis.model;

import lombok.Builder;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
@Builder
public class Game {
    public static final List<String> scores = Arrays.asList("0", "15", "30", "40","ADV","DEUCE");

    public Player player1;
    public Player player2;
    public Player winner;
    public String scorePlayer1;
    public String scorePlayer2;
    public StringBuilder gameScore;


}

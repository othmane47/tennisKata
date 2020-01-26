package tennis.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Set {
    private List<Game> games;
    public Integer setWinner;
    public int scorePlayer1;
    public int scorePlayer2;

}

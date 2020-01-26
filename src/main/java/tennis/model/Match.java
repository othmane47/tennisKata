package tennis.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Match {

    private Player player1;
    private Player player2;
    private Player matchWinner;
    private List<Set> sets;

}

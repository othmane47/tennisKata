package tennis.model;

import lombok.Builder;
import lombok.Data;

/**
 * The type Match.
 */
@Data
@Builder
public class Match {

    private Player player1;
    private Player player2;
    private Player matchWinner;
    //In real rules that should be a List of set
    private Set set;

}

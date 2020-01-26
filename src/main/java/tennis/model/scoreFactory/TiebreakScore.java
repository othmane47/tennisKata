package tennis.model.scoreFactory;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TiebreakScore implements Score {

    public int scorePlayer1;
    public int scorePlayer2;

    @Override
    public String print() {
        return null;
    }
}

package tennis.model.scoreFactory;

import lombok.Builder;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
@Builder
public class GameScore implements Score {
    public static final List<String> scores = Arrays.asList("0", "15", "30", "40","ADV","DEUCE");
    private String scorePlayer1;
    private String scorePlayer2;
    @Override
    public String print() {
        return null;
    }
}

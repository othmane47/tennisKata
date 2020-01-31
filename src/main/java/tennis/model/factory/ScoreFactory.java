package tennis.model.factory;

import tennis.exception.NotAllowedException;

/**
 * The type Score factory.
 */
public class ScoreFactory implements AbstractFactory<Score> {
    @Override
    public Score create(String scoreType, int scorePlayer1, int scorePlayer2) throws NotAllowedException {
        switch (scoreType) {
            case "SetScore": {
                return SetScore.builder()
                        .scorePlayer1(scorePlayer1)
                        .scorePlayer2(scorePlayer2).build();
            }
            case "TiebreakScore": {
                return TiebreakScore.builder()
                        .scorePlayer1(scorePlayer1)
                        .scorePlayer2(scorePlayer2).build();
            }
            default: throw new NotAllowedException("Score type not allowed");
        }
    }

    @Override
    public Score create(String scoreType, String scorePlayer1, String scorePlayer2) throws NotAllowedException {
        switch (scoreType) {
            case "GameScore": {
                return GameScore.builder()
                        .scorePlayer1(scorePlayer1)
                        .scorePlayer2(scorePlayer2).build();
            }
            default: throw new NotAllowedException("Score type not allowed");

        }
    }
}

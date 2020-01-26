package tennis.service;

import tennis.model.Game;
import tennis.model.Set;
import tennis.model.scoreFactory.SetScore;
import tennis.model.scoreFactory.TiebreakScore;

import java.util.ArrayList;

public class SetService implements ISetService {

    private SetScore newScore;

    @Override
    public Set createSet() {
        newScore = SetScore.builder()
                .scorePlayer1(0)
                .scorePlayer2(0)
                .build();
        Set set=Set.builder()
                .setScore(new ArrayList<SetScore>())
                .games(new ArrayList<>())
                .build();
        set.getSetScore().add(newScore);

        return set;
    }

    @Override
    public void playGame(Set set, Game game) throws Exception {
        if (game.getGameWinner() == null)
            throw new Exception("Cant add unfinished game to the set");
        if (set.getSetWinner() != null)
            throw new Exception("The set is over");
        SetScore setScore = this.getSetScore(set);

        set.getGames().add(game);
        if (game.getGameWinner() == 1) {
            newScore = SetScore.builder()
                    .scorePlayer1(setScore.getScorePlayer1() + 1)
                    .scorePlayer2(setScore.getScorePlayer2())
                    .build();
            set.getSetScore().add(newScore);
        } else {
            newScore = SetScore.builder()
                    .scorePlayer1(setScore.getScorePlayer1())
                    .scorePlayer2(setScore.getScorePlayer2() + 1)
                    .build();
            set.getSetScore().add(newScore);
        }

        setScore = this.getSetScore(set);

        if ((setScore.getScorePlayer1() == 6 || setScore.getScorePlayer2() == 6) && (Math.abs(setScore.getScorePlayer1() - setScore.getScorePlayer2()) > 1))
            set.setSetWinner((setScore.getScorePlayer1() > setScore.getScorePlayer2()) ? 1 : 2);
        if ((setScore.getScorePlayer1() == 7 || setScore.getScorePlayer2() == 7))
            set.setSetWinner((setScore.getScorePlayer1() > setScore.getScorePlayer2()) ? 1 : 2);

    }

    @Override
    public SetScore getSetScore(Set set) {
        long count = set.getSetScore().stream().count();
        return (SetScore) set.getSetScore().stream().skip(count - 1).findFirst().get();
    }

    @Override
    public TiebreakScore getTieScore(Set set) throws Exception {
        if (set.getTieScore() == null)
            throw new Exception("No tiebreak in this set");
        long count = set.getTieScore().stream().count();
        return (TiebreakScore) set.getTieScore().stream().skip(count - 1).findFirst().get();
    }


}

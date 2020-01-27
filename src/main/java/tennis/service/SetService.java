package tennis.service;

import tennis.model.Game;
import tennis.model.Set;
import tennis.model.scoreFactory.SetScore;
import tennis.model.scoreFactory.TiebreakScore;

import java.util.ArrayList;
import java.util.Optional;

public class SetService implements ISetService {

    private SetScore newScore;
    private TiebreakScore newTieScore;

    @Override
    public Set createSet() {
        newScore = SetScore.builder()
                .scorePlayer1(0)
                .scorePlayer2(0)
                .build();
        Set set = Set.builder()
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
        TiebreakScore tieScore;

        set.getGames().add(game);
        if (set.getTieScore() == null) {
            newScore = SetScore.builder()
                    .scorePlayer1((game.getGameWinner() == 1) ? setScore.getScorePlayer1() + 1 : setScore.getScorePlayer1())
                    .scorePlayer2((game.getGameWinner() == 2) ? setScore.getScorePlayer2() + 1 : setScore.getScorePlayer2())
                    .build();
            set.getSetScore().add(newScore);

            setScore = this.getSetScore(set);

            if ((setScore.getScorePlayer1() == 6 || setScore.getScorePlayer2() == 6) && (Math.abs(setScore.getScorePlayer1() - setScore.getScorePlayer2()) > 1))
                set.setSetWinner((setScore.getScorePlayer1() > setScore.getScorePlayer2()) ? 1 : 2);
            if ((setScore.getScorePlayer1() == 7 || setScore.getScorePlayer2() == 7) && (Math.abs(setScore.getScorePlayer1() - setScore.getScorePlayer2()) == 2))
                set.setSetWinner((setScore.getScorePlayer1() > setScore.getScorePlayer2()) ? 1 : 2);
            if (setScore.getScorePlayer1() == 6 && setScore.getScorePlayer2() == 6)
                set.setTieScore(new ArrayList<TiebreakScore>());
        } else {

            if (set.getTieScore().size() == 0) {
                newTieScore = TiebreakScore.builder()
                        .scorePlayer1((game.getGameWinner() == 1) ? 1 : 0)
                        .scorePlayer2((game.getGameWinner() == 2) ? 1 : 0)
                        .build();
                set.getTieScore().add(newTieScore);
            } else {
                tieScore = this.getTieScore(set);

                newTieScore = TiebreakScore.builder()
                        .scorePlayer1((game.getGameWinner() == 1) ? tieScore.getScorePlayer1() + 1 : tieScore.getScorePlayer1())
                        .scorePlayer2((game.getGameWinner() == 2) ? tieScore.getScorePlayer2() + 1 : tieScore.getScorePlayer2())
                        .build();
                set.getTieScore().add(newTieScore);
                tieScore = this.getTieScore(set);

                if (tieScore.scorePlayer1 >= 7 && tieScore.scorePlayer2 >= 7 && (Math.abs(tieScore.getScorePlayer1() - tieScore.getScorePlayer2()) == 2)){
                    set.setSetWinner((tieScore.getScorePlayer1() > tieScore.getScorePlayer2()) ? 1 : 2);}
            }
        }

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
        if (count == 0)
            throw new Exception("Tiebreak score is empty");
        return (TiebreakScore) set.getTieScore().stream().skip(count - 1).findFirst().get();
    }


}

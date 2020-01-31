package tennis.service;

import lombok.extern.java.Log;
import tennis.exception.GameOverException;
import tennis.exception.NotAllowedException;
import tennis.model.Game;
import tennis.model.Set;
import tennis.model.factory.ScoreFactory;
import tennis.model.factory.SetScore;
import tennis.model.factory.TiebreakScore;

import java.util.ArrayList;
import java.util.Optional;

/**
 * The type Set service.
 */
@Log
public class SetService implements ISetService {

    private ScoreFactory scoreFactory = new ScoreFactory();
    private TiebreakScore tieScore;


    @Override
    public Set createSet() throws NotAllowedException {
        Set set = Set.builder()
                .setScore(new ArrayList<SetScore>())
                .games(new ArrayList<>())
                .build();
        addScoreToSet(set, 0, 0);
        return set;
    }

    @Override
    public void playGame(Set set, Game game) throws GameOverException, NotAllowedException {
        SetScore setScore;

        if (game.getGameWinner() == null)
            throw new GameOverException("Cant add unfinished game to the set");
        if (set.getSetWinner() != null)
            throw new GameOverException("The set is over");
        set.getGames().add(game);
        if (set.getTieScore() == null) {
            addScoreToSet(set, (game.getGameWinner() == 1) ? getSetScore(set).getScorePlayer1() + 1 : getSetScore(set).getScorePlayer1(),
                    (game.getGameWinner() == 2) ? getSetScore(set).getScorePlayer2() + 1 : getSetScore(set).getScorePlayer2());
            setScore = this.getSetScore(set);

            if ((setScore.getScorePlayer1() == 6
                    || setScore.getScorePlayer2() == 6)
                    && (Math.abs(setScore.getScorePlayer1() - setScore.getScorePlayer2()) > 1))
                set.setSetWinner((setScore.getScorePlayer1() > setScore.getScorePlayer2()) ? 1 : 2);

            if ((setScore.getScorePlayer1() == 7
                    || setScore.getScorePlayer2() == 7)
                    && (Math.abs(setScore.getScorePlayer1() - setScore.getScorePlayer2()) == 2))
                set.setSetWinner((setScore.getScorePlayer1() > setScore.getScorePlayer2()) ? 1 : 2);

            if (setScore.getScorePlayer1() == 6 && setScore.getScorePlayer2() == 6)
                set.setTieScore(new ArrayList<>());
        } else {
            if (set.getTieScore().isEmpty()) {
                addScoreToTiebreak(set, (game.getGameWinner() == 1) ? 1 : 0, (game.getGameWinner() == 2) ? 1 : 0);

            } else {
                tieScore = this.getTieScore(set);
                addScoreToTiebreak(set, (game.getGameWinner() == 1) ? tieScore.getScorePlayer1() + 1 : tieScore.getScorePlayer1()
                        , (game.getGameWinner() == 2) ? tieScore.getScorePlayer2() + 1 : tieScore.getScorePlayer2());

                tieScore = this.getTieScore(set);
                if (tieScore.getScorePlayer1() >= 7
                        && tieScore.getScorePlayer2() >= 7
                        && (Math.abs(tieScore.getScorePlayer1() - tieScore.getScorePlayer2()) == 2)) {
                    set.setSetWinner((tieScore.getScorePlayer1() > tieScore.getScorePlayer2()) ? 1 : 2);
                }
            }
        }
        printScore(set);
    }


    @Override
    public SetScore getSetScore(Set set) {
        long count = set.getSetScore().stream().count();
        Optional<SetScore> lastSetScore = set.getSetScore().stream().skip(count - 1).findFirst();
        if (lastSetScore.isPresent())
            return lastSetScore.get();
        return null;
    }

    @Override
    public TiebreakScore getTieScore(Set set) throws NotAllowedException {
        if (set.getTieScore() == null)
            throw new NotAllowedException("No tiebreak in this set");
        long count = set.getTieScore().stream().count();
        Optional<TiebreakScore> tiebreakScore = set.getTieScore().stream().skip(count - 1).findFirst();
        if (tiebreakScore.isPresent())
            return tiebreakScore.get();
        return null;
    }

    @Override
    public void addScoreToSet(Set set, int scorePlayer1, int scorePlayer2) throws NotAllowedException {
        SetScore newScore = (SetScore) scoreFactory.create("SetScore", scorePlayer1, scorePlayer2);
        set.getSetScore().add(newScore);
    }

    @Override
    public void addScoreToTiebreak(Set set, int scorePlayer1, int scorePlayer2) throws NotAllowedException {
        tieScore = (TiebreakScore) scoreFactory.create("TiebreakScore", scorePlayer1, scorePlayer2);
        set.getTieScore().add(tieScore);

    }

    @Override
    public void printScore(Set set) throws NotAllowedException {
        StringBuilder trace = getSetScore(set).trace();

        if(set.getTieScore()!=null && !set.getTieScore().isEmpty())
            trace.append(getTieScore(set).trace()) ;
        if (set.getSetWinner() != null)
            trace.append(" Set winner is : Player " + set.getSetWinner());

        log.info(trace.toString());
    }


}

package tennis.service;

import lombok.extern.java.Log;
import tennis.exception.NotAllowedException;
import tennis.model.Match;
import tennis.model.Player;
import tennis.model.Set;

@Log
public class MatchService implements IMatchService {

    @Override
    public Match createMatch(Player player1, Player player2) {
        return Match.builder()
                .player1(player1)
                .player2(player2)
                .build();
    }

    @Override
    public void playSet(Match match, Set set) throws NotAllowedException {
        if(set.getSetWinner()==null)
            throw new NotAllowedException("set shoud be finished");
        match.setMatchWinner((set.getSetWinner()==1)?match.getPlayer1():match.getPlayer2());
        printFinalScore(match);
    }

    @Override
    public void printFinalScore(Match match) {
        log.info("The Match winner is : "+match.getMatchWinner().getName());

    }
}

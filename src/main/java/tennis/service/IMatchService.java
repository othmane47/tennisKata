package tennis.service;

import tennis.exception.NotAllowedException;
import tennis.model.Match;
import tennis.model.Player;
import tennis.model.Set;

public interface IMatchService {

    Match createMatch(Player player1, Player player2);
    void playSet(Match match, Set set) throws NotAllowedException;
    void printFinalScore(Match match);
}

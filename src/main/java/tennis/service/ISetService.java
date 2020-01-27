package tennis.service;

import tennis.model.Game;
import tennis.model.Set;
import tennis.model.scoreFactory.SetScore;
import tennis.model.scoreFactory.TiebreakScore;

import java.util.Optional;

public interface ISetService {

    Set createSet();
    void playGame (Set set,Game game) throws Exception;
    SetScore getSetScore(Set set);
    TiebreakScore getTieScore(Set set) throws Exception;

}

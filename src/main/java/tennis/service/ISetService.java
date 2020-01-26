package tennis.service;

import tennis.model.Game;
import tennis.model.Set;

public interface ISetService {

    Set createSet();
    void playGame (Set set,Game game) throws Exception;
}

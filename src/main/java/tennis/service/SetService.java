package tennis.service;

import tennis.model.Game;
import tennis.model.Set;

import java.util.ArrayList;

public class SetService implements ISetService {


    @Override
    public Set createSet() {
        return Set.builder()
                .scorePlayer1(0)
                .scorePlayer2(0)
                .games(new ArrayList<>())
                .build();
    }

    @Override
    public void playGame(Set set, Game game) throws Exception{
        if (game.getGameWinner()==null)
            throw new Exception("Cant add unfinished game to the set");
        if(set.getSetWinner()!=null)
            throw new Exception("Th set is over");


        set.getGames().add(game);
        if(game.getGameWinner()==1)
            set.scorePlayer1+=1;
        else
            set.scorePlayer2+=1;

        if((set.getScorePlayer1()==6||set.getScorePlayer2()==6)&&(Math.abs(set.getScorePlayer1()-set.getScorePlayer2())>1))
            set.setSetWinner((set.getScorePlayer1()>set.getScorePlayer2())?1:2);
        if((set.getScorePlayer1()==7||set.getScorePlayer2()==7))
            set.setSetWinner((set.getScorePlayer1()>set.getScorePlayer2())?1:2);

    }


}

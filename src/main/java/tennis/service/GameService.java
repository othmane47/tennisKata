package tennis.service;

import tennis.model.Game;
import tennis.model.scoreFactory.GameScore;
import tennis.model.scoreFactory.Score;

import java.util.ArrayList;

import static tennis.model.scoreFactory.GameScore.scores;

public class GameService implements IGameService {

    private PlayerService playerService=new PlayerService();
    private  GameScore newScore;

    @Override
    public Game createGame() {
        GameScore gameScore=GameScore.builder()
                .scorePlayer1("0")
                .scorePlayer2("0")
                .build();

        Game game=Game.builder()
                .gameScore(new ArrayList<Score>())
                .build();
        game.getGameScore().add(gameScore);

        return game;
    }

    @Override
    public void play(Game game,int serviceWinner) {
        GameScore gameScore=this.getGameScore(game);
        if(game.getGameWinner()==null) {
            if(serviceWinner==1) {
                if (scores.indexOf(gameScore.getScorePlayer1())<3 ){
                     newScore=GameScore.builder()
                                        .scorePlayer1(scores.get(scores.indexOf(gameScore.getScorePlayer1()) + 1))
                                        .scorePlayer2(gameScore.getScorePlayer2())
                                        .build();
                    game.getGameScore().add(newScore);
                }
                else if(scores.indexOf(gameScore.getScorePlayer2())==3 && scores.indexOf(gameScore.getScorePlayer1())==3){
                     newScore=GameScore.builder()
                            .scorePlayer1(scores.get(4))
                            .scorePlayer2(gameScore.getScorePlayer2())
                            .build();
                    game.getGameScore().add(newScore);
                }
                else if(scores.indexOf(gameScore.getScorePlayer2())==4){
                     newScore=GameScore.builder()
                            .scorePlayer1(scores.get(5))
                            .scorePlayer2(scores.get(5))
                            .build();
                    game.getGameScore().add(newScore);
                }
                else if(scores.indexOf(gameScore.getScorePlayer1())==5 && scores.indexOf(gameScore.getScorePlayer2())==5){
                     newScore=GameScore.builder()
                            .scorePlayer1(scores.get(4))
                            .scorePlayer2(scores.get(3))
                            .build();
                    game.getGameScore().add(newScore);
                }
                else {
                    game.setGameWinner(1);
                    resetPlayersScore(game);
                }
            }
            else if(serviceWinner==2)
            {
                if (scores.indexOf(gameScore.getScorePlayer2())<3 ){
                     newScore=GameScore.builder()
                            .scorePlayer2(scores.get(scores.indexOf(gameScore.getScorePlayer2()) + 1))
                            .scorePlayer1(gameScore.getScorePlayer1())
                            .build();
                    game.getGameScore().add(newScore);
                }
                else if(scores.indexOf(gameScore.getScorePlayer1())==3 && scores.indexOf(gameScore.getScorePlayer2())==3){
                     newScore=GameScore.builder()
                            .scorePlayer2(scores.get(4))
                            .scorePlayer1(gameScore.getScorePlayer1())
                            .build();
                    game.getGameScore().add(newScore);
                }
                else if(scores.indexOf(gameScore.getScorePlayer1())==4){
                     newScore=GameScore.builder()
                            .scorePlayer2(scores.get(5))
                            .scorePlayer1(scores.get(5))
                            .build();
                    game.getGameScore().add(newScore);
                }
                else if(scores.indexOf(gameScore.getScorePlayer1())==5 && scores.indexOf(gameScore.getScorePlayer2())==5){
                     newScore=GameScore.builder()
                            .scorePlayer1(scores.get(3))
                            .scorePlayer2(scores.get(4))
                            .build();
                    game.getGameScore().add(newScore);

                }
                else{
                    game.setGameWinner(2);
                    resetPlayersScore(game);
                }
            }
        }


    }

    @Override
    public void resetPlayersScore(Game game) {
         newScore=GameScore.builder()
                .scorePlayer1(scores.get(0))
                .scorePlayer2(scores.get(0))
                .build();
        game.getGameScore().add(newScore);
    }

    @Override
    public GameScore getGameScore(Game game){
        long count = game.getGameScore().stream().count();
        return (GameScore)game.getGameScore().stream().skip(count - 1).findFirst().get();
    }

}

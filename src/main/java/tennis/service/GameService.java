package tennis.service;

import tennis.model.Game;

import static tennis.model.Game.scores;

public class GameService implements IGameService {

    private PlayerService playerService=new PlayerService();

    @Override
    public Game createGame(String player1Name, String player2Name) {

        return Game.builder()
                .player1(playerService.createPlayer(player1Name))
                .player2(playerService.createPlayer(player2Name))
                .scorePlayer1("0")
                .scorePlayer2("0")
                .gameScore(new StringBuilder(player1Name+":"+player2Name+"|0:0"))
                .build();
    }

    @Override
    public void play(Game game,int serviceWinner) {
        if(game.getWinner()==null) {
            if(serviceWinner==1) {
                if (scores.indexOf(game.getScorePlayer1())<3 ){
                    game.setScorePlayer1(scores.get(scores.indexOf(game.getScorePlayer1()) + 1));
                    updateScore(game);
                }
                else if(scores.indexOf(game.getScorePlayer2())==3 && scores.indexOf(game.getScorePlayer1())==3){
                    game.setScorePlayer1(scores.get(4));
                    updateScore(game);
                }
                else if(scores.indexOf(game.getScorePlayer2())==4){
                    game.setScorePlayer1(scores.get(5));
                    game.setScorePlayer2(scores.get(5));
                    updateScore(game);
                }
                else if(scores.indexOf(game.getScorePlayer1())==5 && scores.indexOf(game.getScorePlayer2())==5){
                    game.setScorePlayer1(scores.get(4));
                    game.setScorePlayer2(scores.get(3));
                    updateScore(game);
                }
                else {
                    game.setWinner(game.getPlayer1());
                    updateScore(game);
                    resetPlayersScore(game);
                }
            }
            else if(serviceWinner==2)
            {
                if (scores.indexOf(game.getScorePlayer2())<3 ){
                    game.setScorePlayer2(scores.get(scores.indexOf(game.getScorePlayer2()) + 1));
                    updateScore(game);
                }
                else if(scores.indexOf(game.getScorePlayer1())==3 && scores.indexOf(game.getScorePlayer2())==3){
                    game.setScorePlayer2(scores.get(4));
                    updateScore(game);
                }
                else if(scores.indexOf(game.getScorePlayer1())==4){
                    game.setScorePlayer1(scores.get(5));
                    game.setScorePlayer2(scores.get(5));
                    updateScore(game);
                }
                else if(scores.indexOf(game.getScorePlayer1())==5 && scores.indexOf(game.getScorePlayer2())==5){
                    game.setScorePlayer1(scores.get(3));
                    game.setScorePlayer2(scores.get(4));
                    updateScore(game);
                }
                else{
                    game.setWinner(game.getPlayer2());
                    updateScore(game);
                    resetPlayersScore(game);
                }
            }
        }


    }
    @Override
    public void updateScore(Game game) {
        if(game.getWinner()!=null)
         game.setGameScore(game.getGameScore().append("|"+game.getWinner().getName()+" win the game"));
        else
         game.setGameScore(game.getGameScore().append("|"+game.getScorePlayer1()+":"+game.getScorePlayer2()));
    }

    @Override
    public void resetPlayersScore(Game game) {
        game.setScorePlayer1("0");
        game.setScorePlayer2("0");
    }

}

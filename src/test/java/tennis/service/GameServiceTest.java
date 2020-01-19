package tennis.service;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tennis.model.Game;

import static org.assertj.core.api.Assertions.*;


public class GameServiceTest {

    public static GameService gameService=new GameService();

    Game game;

    @BeforeEach
    public void setUp(){
        System.out.println("before");
        game=gameService.createGame("Nadal","Federer");

    }

    @Test
    public void shouldCreateGame(){
        Game game=gameService.createGame("Nadal","Federer");
        assertThat(game).isNotNull();
    }
    @Test
    public void shouldInitScoreBy0(){
        Game game=gameService.createGame("Nadal","Federer");
        assertThat(game.getScorePlayer1()).isEqualTo("0");
    }

    @Test
    public void shouldAddPointToPlayer1(){
        gameService.play(game,1);
        assertThat(game.getScorePlayer1()).isEqualTo("15");
    }

    @Test
    public void shouldGetPlayerOneAsWinner(){
        gameService.play(game,1);
        gameService.play(game,1);
        gameService.play(game,1);
        gameService.play(game,1);

        assertThat(game.getWinner()).isEqualTo(game.getPlayer1());

    }
    @Test
    public void shouldGetPlayerTwoAsWinner(){
        gameService.play(game,2);
        gameService.play(game,2);
        gameService.play(game,2);
        gameService.play(game,2);
        assertThat(game.getWinner()).isEqualTo(game.getPlayer2());

    }
    @Test
    public void shouldUpdateScoreTrace(){
        gameService.play(game,1);
        gameService.play(game,2);
        gameService.play(game,1);
        gameService.play(game,1);
        gameService.play(game,1);

        assertThat(game.getGameScore()).isEqualToIgnoringCase("Nadal:Federer|0:0|15:0|15:15|30:15|40:15|Nadal win the game");

    }

    @Test
    public void shouldResetPlayersScoreAfterAWin(){
        gameService.play(game,1);
        gameService.play(game,1);
        gameService.resetPlayersScore(game);
        assertThat(game.getScorePlayer1()).isEqualTo("0");
    }

}
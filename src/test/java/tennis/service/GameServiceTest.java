package tennis.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tennis.model.Game;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;


public class GameServiceTest {

    public static GameService gameService=new GameService();

    Game game;

    @BeforeEach
    public void setUp(){
        game=gameService.createGame();

    }

    @Test
    public void shouldCreateGame(){
        Game game=gameService.createGame();
        assertThat(game).isNotNull();
    }
    @Test
    public void shouldInitScoreBy0(){
        Game game=gameService.createGame();
        assertThat(game.getScorePlayer1()).isEqualTo("0");
    }

    @Test
    public void shouldAddPointToPlayer1(){
        gameService.play(game,1);
        assertThat(game.getScorePlayer1()).isEqualTo("15");
    }

    @Test
    public void shouldGetPlayerOneAsWinner(){
        IntStream.range(0,4).forEach(i -> gameService.play(game,1));

        assertThat(game.getGameWinner()).isEqualTo(1);

    }
    @Test
    public void shouldGetPlayerTwoAsWinner(){
        IntStream.range(0,4).forEach(i -> gameService.play(game,2));

        assertThat(game.getGameWinner()).isEqualTo(2);

    }
    @Test
    public void shouldUpdateScoreTrace(){
        gameService.play(game,1);
        gameService.play(game,2);
        IntStream.range(0,3).forEach(i -> gameService.play(game,1));

        assertThat(game.getGameScore()).isEqualToIgnoringCase("0:0|15:0|15:15|30:15|40:15|0:0|Player 1 win the game");

    }

    @Test
    public void shouldResetPlayersScoreAfterAWin(){
        gameService.play(game,1);
        gameService.play(game,1);
        gameService.resetPlayersScore(game);

        assertThat(game.getScorePlayer1()).isEqualTo("0");
    }

    //Sprint 2


    @Test
    public void shouldGetAdvantageInScore(){
        IntStream.range(0,3).forEach(i -> gameService.play(game,1));
        IntStream.range(0,3).forEach(i -> gameService.play(game,2));
        gameService.play(game,1); //40
        assertThat(game.getScorePlayer1()).isEqualTo("ADV");
    }

    @Test
    public void shouldGetDeuceInScore(){
        IntStream.range(0,3).forEach(i -> gameService.play(game,2));
        IntStream.range(0,4).forEach(i -> gameService.play(game,1));
        gameService.play(game,2); //DEUCE
        assertThat(game.getScorePlayer1()).isEqualTo("DEUCE");
    }

    @Test
    public void shouldGetAdvantageAfterADeuce(){
        IntStream.range(0,3).forEach(i -> gameService.play(game,2));
        IntStream.range(0,4).forEach(i -> gameService.play(game,1));
        gameService.play(game,2); //DEUCE
        gameService.play(game,1); //ADV
        assertThat(game.getScorePlayer1()).isEqualTo("ADV");
    }

}
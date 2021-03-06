package tennis.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tennis.exception.GameOverException;
import tennis.exception.NotAllowedException;
import tennis.model.Game;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;


/**
 * The type Game service test.
 */
public class GameServiceTest {

    /**
     * The constant gameService.
     */
    static GameService gameService;
    /**
     * The Game.
     */
    Game game;

    @BeforeAll
    public static void setUp(){
        gameService=new GameService();
    }

    /**
     * Init method.
     */
    @BeforeEach
    public void init() throws NotAllowedException {
        game=gameService.createGame();

    }

    /**
     * Should create game.
     */
    @Test
    public void shouldCreateGame() throws NotAllowedException {
        Game game=gameService.createGame();
        assertThat(game).isNotNull();
    }

    /**
     * Should init score by 0.
     */
    @Test
    public void shouldInitScoreBy0() throws NotAllowedException {
        Game game=gameService.createGame();
        assertThat(gameService.getGameScore(game).getScorePlayer1()).isEqualTo("0");
    }

    /**
     * Should throw game over exception.
     */
    @Test
    public void shouldThrowGameOverException(){
        IntStream.range(0,4).forEach(i -> {
            try {
                gameService.play(game,2);
            } catch (GameOverException e) {
                e.printStackTrace();
            } catch (NotAllowedException e) {
                e.printStackTrace();
            }
        });
        assertThatThrownBy(() ->gameService.play(game,2))
                .isInstanceOf(GameOverException.class)
                .hasMessageContaining("The game is over");
    }

    /**
     * Should add point to player 1.
     *
     * @throws GameOverException the game over exception
     */
    @Test
    public void shouldAddPointToPlayer1() throws GameOverException, NotAllowedException {
        gameService.play(game,1);
        assertThat(gameService.getGameScore(game).getScorePlayer1()).isEqualTo("15");
    }

    /**
     * Should get player one as winner.
     */
    @Test
    public void shouldGetPlayerOneAsWinner(){
        IntStream.range(0,4).forEach(i -> {
            try {
                gameService.play(game,1);
            } catch (GameOverException e) {
                e.printStackTrace();
            } catch (NotAllowedException e) {
                e.printStackTrace();
            }
        });

        assertThat(game.getGameWinner()).isEqualTo(1);

    }

    /**
     * Should get player two as winner.
     */
    @Test
    public void shouldGetPlayerTwoAsWinner(){
        IntStream.range(0,4).forEach(i -> {
            try {
                gameService.play(game,2);
            } catch (GameOverException e) {
                e.printStackTrace();
            } catch (NotAllowedException e) {
                e.printStackTrace();
            }
        });

        assertThat(game.getGameWinner()).isEqualTo(2);

    }

    /**
     * Should reset players score after a win.
     *
     * @throws GameOverException the game over exception
     */
    @Test
    public void shouldResetPlayersScoreAfterAWin() throws GameOverException, NotAllowedException {
        gameService.play(game,1);
        gameService.play(game,1);
        gameService.resetPlayersScore(game);

        assertThat(gameService.getGameScore(game).getScorePlayer1()).isEqualTo("0");
    }

    //Sprint 2


    /**
     * Should get advantage in score.
     *
     * @throws GameOverException the game over exception
     */
    @Test
    public void shouldGetAdvantageInScore() throws GameOverException, NotAllowedException {
        IntStream.range(0,3).forEach(i -> {
            try {
                gameService.play(game,1);
            } catch (GameOverException e) {
                e.printStackTrace();
            } catch (NotAllowedException e) {
                e.printStackTrace();
            }
        });
        IntStream.range(0,3).forEach(i -> {
            try {
                gameService.play(game,2);
            } catch (GameOverException e) {
                e.printStackTrace();
            } catch (NotAllowedException e) {
                e.printStackTrace();
            }
        });
        gameService.play(game,1); //40
        assertThat(gameService.getGameScore(game).getScorePlayer1()).isEqualTo("ADV");
    }

    /**
     * Should get deuce in score.
     *
     * @throws GameOverException the game over exception
     */
    @Test
    public void shouldGetDeuceInScore() throws GameOverException, NotAllowedException {
        IntStream.range(0,3).forEach(i -> {
            try {
                gameService.play(game,2);
            } catch (GameOverException e) {
                e.printStackTrace();
            } catch (NotAllowedException e) {
                e.printStackTrace();
            }
        });
        IntStream.range(0,4).forEach(i -> {
            try {
                gameService.play(game,1);
            } catch (GameOverException e) {
                e.printStackTrace();
            } catch (NotAllowedException e) {
                e.printStackTrace();
            }
        });
        gameService.play(game,2); //DEUCE
        assertThat(gameService.getGameScore(game).getScorePlayer1()).isEqualTo("DEUCE");
    }

    /**
     * Should get advantage after a deuce.
     *
     * @throws GameOverException the game over exception
     */
    @Test
    public void shouldGetAdvantageAfterADeuce() throws GameOverException, NotAllowedException {
        IntStream.range(0,3).forEach(i -> {
            try {
                gameService.play(game,2);
            } catch (GameOverException e) {
                e.printStackTrace();
            } catch (NotAllowedException e) {
                e.printStackTrace();
            }
        });
        IntStream.range(0,4).forEach(i -> {
            try {
                gameService.play(game,1);
            } catch (GameOverException e) {
                e.printStackTrace();
            } catch (NotAllowedException e) {
                e.printStackTrace();
            }
        });
        gameService.play(game,2); //DEUCE
        gameService.play(game,1); //ADV
        assertThat(gameService.getGameScore(game).getScorePlayer1()).isEqualTo("ADV");
    }

}
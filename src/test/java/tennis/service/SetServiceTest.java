package tennis.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tennis.exception.GameOverException;
import tennis.model.Game;
import tennis.model.Set;
import tennis.model.factory.SetScore;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

/**
 * The type Set service test.
 */
class SetServiceTest {

    /**
     * The Set service.
     */
    SetService setService=new SetService();
    /**
     * The Game service.
     */
    GameService gameService=new GameService();
    /**
     * The Set.
     */
    Set set;
    /**
     * The Game.
     */
    Game game;


    /**
     * Set up.
     */
    @BeforeEach
    public void setUp(){
        set=setService.createSet();
    }

    /**
     * Should create set.
     */
    @Test
    public void shouldCreateSet(){

        Set set=setService.createSet();

        assertThat(set).isNotNull();
    }

    /**
     * Should add games to set.
     *
     * @throws Exception the exception
     */
    @Test
    public void shouldAddGamesToSet() throws Exception {
        Game game= gameService.createGame();

        gameService.play(game,1);
        gameService.play(game,2);
        IntStream.range(0,3).forEach(i -> {
            try {
                gameService.play(game,1);
            } catch (GameOverException e) {
                e.printStackTrace();
            }
        });
        setService.playGame(set, game);

        assertThat(set.getGames().size()).isEqualTo(1);
    }

    /**
     * Should throw exception if game is unfinished.
     */
    @Test
    public void shouldThrowExceptionIfGameIsUnfinished(){
        Game game= gameService.createGame();

        assertThatThrownBy(() ->setService.playGame(set, game))
                .isInstanceOf(Exception.class)
                .hasMessageContaining("Cant add unfinished game to the set");
    }

    /**
     * Should game result update set one.
     *
     * @throws Exception the exception
     */
    @Test
    public void shouldGameResultUpdateSetOne() throws Exception {
        Game game= gameService.createGame();
        IntStream.range(0,4).forEach(i -> {
            try {
                gameService.play(game,1);
            } catch (GameOverException e) {
                e.printStackTrace();
            }
        });
        setService.playGame(set, game);

        Game game2= gameService.createGame();
        IntStream.range(0,4).forEach(i -> {
            try {
                gameService.play(game2,2);
            } catch (GameOverException e) {
                e.printStackTrace();
            }
        });
        setService.playGame(set, game2);

        assertThat(setService.getSetScore(set).getScorePlayer1()).isEqualTo(1);
        assertThat(setService.getSetScore(set).getScorePlayer2()).isEqualTo(1);
    }

    /**
     * Should player one wins the set with score 6.
     *
     * @throws Exception the exception
     */
    @Test
    public void shouldPlayerOneWinsTheSetWithScore6() throws Exception {
        SetScore newScore = SetScore.builder()
                .scorePlayer1(5)
                .scorePlayer2(4)
                .build();
        set.getSetScore().add(newScore);


        Game game= gameService.createGame();
        IntStream.range(0,4).forEach(i -> {
            try {
                gameService.play(game,1);
            } catch (GameOverException e) {
                e.printStackTrace();
            }
        });
        setService.playGame(set, game);

        assertThat(set.getSetWinner()).isEqualTo(1);
    }

    /**
     * Should player one wins the set with score 7.
     *
     * @throws Exception the exception
     */
    @Test
    public void shouldPlayerOneWinsTheSetWithScore7() throws Exception {
        SetScore newScore = SetScore.builder()
                .scorePlayer1(6)
                .scorePlayer2(5)
                .build();
        set.getSetScore().add(newScore);

        Game game= gameService.createGame();
        IntStream.range(0,4).forEach(i -> {
            try {
                gameService.play(game,1);
            } catch (GameOverException e) {
                e.printStackTrace();
            }
        });
        setService.playGame(set, game);

        assertThat(set.getSetWinner()).isEqualTo(1);
    }

    /**
     * Should active tie break rules.
     *
     * @throws Exception the exception
     */
    @Test
    public void shouldActiveTieBreakRules() throws Exception {
        SetScore newScore = SetScore.builder()
                .scorePlayer1(6)
                .scorePlayer2(5)
                .build();
        set.getSetScore().add(newScore);

        game= gameService.createGame();
        IntStream.range(0,4).forEach(i -> {
            try {
                gameService.play(game,2);
            } catch (GameOverException e) {
                e.printStackTrace();
            }
        });
        setService.playGame(set, game);

        game= gameService.createGame();
        IntStream.range(0,4).forEach(i -> {
            try {
                gameService.play(game,1);
            } catch (GameOverException e) {
                e.printStackTrace();
            }
        });
        setService.playGame(set, game);
        assertThat(setService.getTieScore(set).getScorePlayer1()).isEqualTo(1);
    }

    /**
     * Should player two win with tie break.
     *
     * @throws Exception the exception
     */
    @Test
    public void shouldPlayerTwoWinWithTieBreak() throws Exception {
        SetScore newScore = SetScore.builder()
                .scorePlayer1(6)
                .scorePlayer2(5)
                .build();
        set.getSetScore().add(newScore);

        IntStream.range(0,10).forEach(i ->{
        game= gameService.createGame();
        IntStream.range(0,4).forEach(j -> {
            try {
                gameService.play(game,2);
            } catch (GameOverException e) {
                e.printStackTrace();
            }
        });
            try {
                setService.playGame(set, game);
            } catch (Exception e) {
                e.printStackTrace();
            }

            game= gameService.createGame();
        IntStream.range(0,4).forEach(j -> {
            try {
                gameService.play(game,1);
            } catch (GameOverException e) {
                e.printStackTrace();
            }
        });
            try {
                setService.playGame(set, game);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        IntStream.range(0,3).forEach(i -> {
            game= gameService.createGame();
            IntStream.range(0,4).forEach(j -> {
                try {
                    gameService.play(game,2);
                } catch (GameOverException e) {
                    e.printStackTrace();
                }
            });
            try {
                setService.playGame(set, game);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        assertThat(set.getSetWinner()).isEqualTo(2);

        }



}
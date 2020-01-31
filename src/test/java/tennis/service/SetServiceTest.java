package tennis.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tennis.exception.GameOverException;
import tennis.exception.NotAllowedException;
import tennis.model.Game;
import tennis.model.Set;
import tennis.model.factory.ScoreFactory;
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
    static SetService setService;
    /**
     * The Game service.
     */
    static GameService gameService;

    static ScoreFactory scoreFactory;
    /**
     * The Set.
     */
    Set set;
    /**
     * The Game.
     */
    Game game;

    @BeforeAll
    public static void setUp(){
        setService=new SetService();
        gameService=new GameService();
        scoreFactory=new ScoreFactory();
    }

    /**
     * Init method.
     */
    @BeforeEach
    public void init() throws NotAllowedException {

        set=setService.createSet();
    }

    /**
     * Should create set.
     */
    @Test
    public void shouldCreateSet() throws NotAllowedException {

        Set set=setService.createSet();

        assertThat(set).isNotNull();
    }

    /**
     * Should add games to set.
     *
     * @throws Exception the exception
     */
    @Test
    public void shouldAddGamesToSet() throws NotAllowedException, GameOverException {
        Game game= gameService.createGame();

        gameService.play(game,1);
        gameService.play(game,2);
        IntStream.range(0,3).forEach(i -> {
            try {
                gameService.play(game,1);
            } catch (GameOverException e) {
                e.printStackTrace();
            } catch (NotAllowedException e) {
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
    public void shouldThrowExceptionIfGameIsUnfinished() throws NotAllowedException {
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
            } catch (NotAllowedException e) {
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
            } catch (NotAllowedException e) {
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
        SetScore newScore = (SetScore) scoreFactory.create("SetScore",5,4);
        set.getSetScore().add(newScore);


        Game game= gameService.createGame();
        IntStream.range(0,4).forEach(i -> {
            try {
                gameService.play(game,1);
            } catch (GameOverException e) {
                e.printStackTrace();
            } catch (NotAllowedException e) {
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
        SetScore newScore = (SetScore) scoreFactory.create("SetScore",6,5);
        set.getSetScore().add(newScore);

        Game game= gameService.createGame();
        IntStream.range(0,4).forEach(i -> {
            try {
                gameService.play(game,1);
            } catch (GameOverException e) {
                e.printStackTrace();
            } catch (NotAllowedException e) {
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
    public void shouldActiveTieBreakRules() throws NotAllowedException, GameOverException {
        SetScore newScore = (SetScore) scoreFactory.create("SetScore",6,5);
        set.getSetScore().add(newScore);

        game= gameService.createGame();
        IntStream.range(0,4).forEach(i -> {
            try {
                gameService.play(game,2);
            } catch (GameOverException e) {
                e.printStackTrace();
            } catch (NotAllowedException e) {
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
            } catch (NotAllowedException e) {
                e.printStackTrace();
            }
        });
        setService.playGame(set, game);
        assertThat(setService.getTieScore(set).getScorePlayer1()).isEqualTo(1);
    }

    /**
     * Should player two wins with tie break.
     *
     * @throws Exception the exception
     */
    @Test
    public void shouldPlayerTwoWinWithTieBreak() throws NotAllowedException {
        SetScore newScore = (SetScore) scoreFactory.create("SetScore",6,5);
        set.getSetScore().add(newScore);

        IntStream.range(0,10).forEach(i ->{
            try {
                game= gameService.createGame();
            } catch (NotAllowedException e) {
                e.printStackTrace();
            }
            IntStream.range(0,4).forEach(j -> {
            try {
                gameService.play(game,2);
            } catch (GameOverException e) {
                e.printStackTrace();
            } catch (NotAllowedException e) {
                e.printStackTrace();
            }
            });
            try {
                setService.playGame(set, game);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                game= gameService.createGame();
            } catch (NotAllowedException e) {
                e.printStackTrace();
            }
            IntStream.range(0,4).forEach(j -> {
            try {
                gameService.play(game,1);
            } catch (GameOverException e) {
                e.printStackTrace();
            } catch (NotAllowedException e) {
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
            try {
                game= gameService.createGame();
            } catch (NotAllowedException e) {
                e.printStackTrace();
            }
            IntStream.range(0,4).forEach(j -> {
                try {
                    gameService.play(game,2);
                } catch (GameOverException e) {
                    e.printStackTrace();
                } catch (NotAllowedException e) {
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
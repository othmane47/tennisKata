package tennis.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tennis.exception.GameOverException;
import tennis.exception.NotAllowedException;
import tennis.model.Game;
import tennis.model.Match;
import tennis.model.Player;
import tennis.model.Set;
import tennis.model.factory.ScoreFactory;
import tennis.model.factory.SetScore;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

/**
 * The type Match service test.
 */
class MatchServiceTest {
    /**
     * The Game.
     */
    static Game game;
    /**
     * The Match service.
     */
    static MatchService matchService;
    /**
     * The Set service.
     */
    static SetService setService;
    /**
     * The Game service.
     */
    static GameService gameService;
    /**
     * The Player service.
     */
    static PlayerService playerService;
    /**
     * The Score factory.
     */
    static ScoreFactory scoreFactory;
    /**
     * The Player 1.
     */
    static Player player1;
    /**
     * The Player 2.
     */
    static Player player2;

    /**
     * Sets up.
     */
    @BeforeAll
    public static void setUp() {
        matchService = new MatchService();
        setService = new SetService();
        gameService = new GameService();
        playerService = new PlayerService();
        scoreFactory = new ScoreFactory();
        player1=playerService.createPlayer("Nadal");
        player2=playerService.createPlayer("Federer");
    }


    /**
     * Should create match.
     */
    @Test
    public void shouldCreateMatch(){

        Match match=matchService.createMatch(player1,player2);
        assertThat(match).isNotNull();
    }

    /**
     * Should add set to match.
     *
     * @throws NotAllowedException the not allowed exception
     */
    @Test
    public void shouldAddSetToMatch() throws NotAllowedException {
        Set set=setService.createSet();
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

        Match match=matchService.createMatch(player1,player2);

        matchService.playSet(match,set);
        assertThat(match.getMatchWinner()).isEqualTo(player2);
    }


}
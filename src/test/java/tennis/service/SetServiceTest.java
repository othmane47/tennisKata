package tennis.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tennis.model.Game;
import tennis.model.Set;
import tennis.model.scoreFactory.SetScore;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

class SetServiceTest {

    SetService setService=new SetService();
    GameService gameService=new GameService();
    Set set;


    @BeforeEach
    public void setUp(){
        set=setService.createSet();
    }

    @Test
    public void shouldCreateSet(){

        Set set=setService.createSet();

        assertThat(set).isNotNull();
    }

    @Test
    public void shouldAddGamesToSet() throws Exception {
        Game game= gameService.createGame();

        gameService.play(game,1);
        gameService.play(game,2);
        IntStream.range(0,3).forEach(i -> gameService.play(game,1));
        setService.playGame(set, game);

        assertThat(set.getGames().size()).isEqualTo(1);
    }

    @Test
    public void shouldThrowExceptionIfGameIsUnfinished(){
        Game game= gameService.createGame();

        assertThatThrownBy(() ->setService.playGame(set, game))
                .isInstanceOf(Exception.class)
                .hasMessageContaining("Cant add unfinished game to the set");
    }

    @Test
    public void shouldGameResultUpdateSetOne() throws Exception {
        Game game= gameService.createGame();
        IntStream.range(0,4).forEach(i -> gameService.play(game,1));
        setService.playGame(set, game);

        Game game2= gameService.createGame();
        IntStream.range(0,4).forEach(i -> gameService.play(game2,2));
        setService.playGame(set, game2);

        assertThat(setService.getSetScore(set).getScorePlayer1()).isEqualTo(1);
        assertThat(setService.getSetScore(set).getScorePlayer2()).isEqualTo(1);
    }

    @Test
    public void shouldPlayerOneWinsTheSetWithScore6() throws Exception {
        SetScore newScore = SetScore.builder()
                .scorePlayer1(5)
                .scorePlayer2(4)
                .build();
        set.getSetScore().add(newScore);


        Game game= gameService.createGame();
        IntStream.range(0,4).forEach(i -> gameService.play(game,1));
        setService.playGame(set, game);

        assertThat(set.getSetWinner()).isEqualTo(1);
    }

    @Test
    public void shouldPlayerOneWinsTheSetWithScore7() throws Exception {
        SetScore newScore = SetScore.builder()
                .scorePlayer1(6)
                .scorePlayer2(5)
                .build();
        set.getSetScore().add(newScore);


        Game game= gameService.createGame();
        IntStream.range(0,4).forEach(i -> gameService.play(game,1));
        setService.playGame(set, game);

        assertThat(set.getSetWinner()).isEqualTo(1);
    }



}
package tennis;




import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tennis.model.Game;
import tennis.service.GameService;

import static org.assertj.core.api.Assertions.*;


/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertThat( true ).isTrue();
    }

    Game game;

    GameService gameService;

    @BeforeEach
    public void setUp(){
        game=Game.builder().build();


    }

    @Test
    public void shouldGameStartsWithScrore0(){
        assertThat(game.getScorePlayer1()).isEqualTo(0);
    }

    public void shouldStartGameBetweenTwhoPlayer(){
        game=gameService.createGame("Nadal","Federer");
        assertThat(game.getPlayer1().getName()).isEqualToIgnoringCase("Nadal");
    }
}

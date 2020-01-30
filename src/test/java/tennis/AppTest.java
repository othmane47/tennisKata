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

    /**
     * The Game.
     */
    Game game;

    /**
     * The Game service.
     */
    GameService gameService;

    /**
     * Set up.
     */
    @BeforeEach
    public void setUp(){
        game=Game.builder().build();


    }

}

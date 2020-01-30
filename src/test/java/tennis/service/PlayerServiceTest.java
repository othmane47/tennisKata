package tennis.service;


import org.junit.jupiter.api.Test;
import tennis.model.Player;

import static org.assertj.core.api.Assertions.*;


/**
 * The type Player service test.
 */
public class PlayerServiceTest {

    private static PlayerService playerService=new PlayerService();


    /**
     * Should create player.
     */
    @Test
    public void shouldCreatePlayer() {
        Player player=playerService.createPlayer("Nadal");
        assertThat(player.getName()).isEqualToIgnoringCase("nadal");
    }

}
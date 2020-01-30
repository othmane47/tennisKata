package tennis.service;


import tennis.model.Player;

/**
 * The type Player service.
 */
public class PlayerService implements IPlayerService {
    @Override
    public Player createPlayer(String playerName) {

        return Player.builder()
                .name(playerName)
                .build();
    }
}

package tennis.service;

import tennis.model.Player;

/**
 * The interface Player service.
 */
public interface IPlayerService {
    /**
     * Create player player.
     *
     * @param playerName the player name
     * @return the player
     */
    Player createPlayer(String playerName);
}

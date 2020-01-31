package tennis.service;

import lombok.extern.java.Log;
import tennis.exception.GameOverException;
import tennis.exception.NotAllowedException;
import tennis.model.Game;
import tennis.model.factory.GameScore;
import tennis.model.factory.ScoreFactory;

import java.util.ArrayList;
import java.util.Optional;

import static tennis.model.factory.GameScore.scores;

/**
 * The type Game service.
 */
@Log
public class GameService implements IGameService {

    private ScoreFactory scoreFactory = new ScoreFactory();


    @Override
    public Game createGame() throws NotAllowedException {
        GameScore gameScore = (GameScore) scoreFactory.create("GameScore", "0", "0");
        Game game = Game.builder()
                .gameScore(new ArrayList<GameScore>())
                .build();
        game.getGameScore().add(gameScore);

        return game;
    }

    @Override
    public void play(Game game, int serviceWinner) throws GameOverException, NotAllowedException {
        GameScore gameScore = this.getGameScore(game);
        if (game.getGameWinner() != null)
            throw new GameOverException("The game is over");

        if (serviceWinner == 1) {
            if (scores.indexOf(gameScore.getScorePlayer1()) < 3) {
                addScoreToGame(game, scores.get(scores.indexOf(gameScore.getScorePlayer1()) + 1), gameScore.getScorePlayer2());
            } else if (scores.indexOf(gameScore.getScorePlayer2()) == 3 && scores.indexOf(gameScore.getScorePlayer1()) == 3) {
                addScoreToGame(game, scores.get(4), gameScore.getScorePlayer2());
            } else if (scores.indexOf(gameScore.getScorePlayer2()) == 4) {
                addScoreToGame(game, scores.get(5), scores.get(5));
            } else if (scores.indexOf(gameScore.getScorePlayer1()) == 5 && scores.indexOf(gameScore.getScorePlayer2()) == 5) {
                addScoreToGame(game, scores.get(4), scores.get(3));
            } else {
                game.setGameWinner(1);
                resetPlayersScore(game);
            }
        } else if (serviceWinner == 2) {
            if (scores.indexOf(gameScore.getScorePlayer2()) < 3) {
                addScoreToGame(game, gameScore.getScorePlayer1(), scores.get(scores.indexOf(gameScore.getScorePlayer2()) + 1));
            } else if (scores.indexOf(gameScore.getScorePlayer1()) == 3 && scores.indexOf(gameScore.getScorePlayer2()) == 3) {
                addScoreToGame(game, gameScore.getScorePlayer1(), scores.get(4));
            } else if (scores.indexOf(gameScore.getScorePlayer1()) == 4) {
                addScoreToGame(game, scores.get(5), scores.get(5));
            } else if (scores.indexOf(gameScore.getScorePlayer1()) == 5 && scores.indexOf(gameScore.getScorePlayer2()) == 5) {
                addScoreToGame(game, scores.get(3), scores.get(4));
            } else {
                game.setGameWinner(2);
                resetPlayersScore(game);
            }
        }
        printScore(game);
    }

    @Override
    public void resetPlayersScore(Game game) throws NotAllowedException {
        addScoreToGame(game, "0", "0");
    }

    @Override
    public GameScore getGameScore(Game game) {
        long count = game.getGameScore().stream().count();
        Optional<GameScore> gameScore = game.getGameScore().stream()
                .skip(count - 1)
                .findFirst();
        if (gameScore.isPresent())
            return gameScore.get();
        return null;

    }

    @Override
    public void addScoreToGame(Game game, String scorePlayer1, String scorePlayer2) throws NotAllowedException {
        GameScore gameScore = (GameScore) scoreFactory.create("GameScore", scorePlayer1, scorePlayer2);
        game.getGameScore().add(gameScore);
    }

    @Override
    public void printScore(Game game) {
        StringBuilder trace = getGameScore(game).trace();
        if (game.getGameWinner() != null)
            trace.append(" Game winner is : Player " + game.getGameWinner());
        log.info(trace.toString());
    }

}

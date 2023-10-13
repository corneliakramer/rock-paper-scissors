package org.corneliakramer.service;

import org.corneliakramer.exception.NoWinnerException;
import org.corneliakramer.model.RockPaperScissor;
import org.corneliakramer.model.RockPaperScissorsGame;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class GameService {

    // Enum hashmap with winning combinations (winner, loser)
    private static final Map<RockPaperScissor, RockPaperScissor> winningMoves;
    static {
        winningMoves = new HashMap<>();
        winningMoves.put(RockPaperScissor.ROCK, RockPaperScissor.SCISSORS);
        winningMoves.put(RockPaperScissor.SCISSORS, RockPaperScissor.PAPER);
        winningMoves.put(RockPaperScissor.PAPER, RockPaperScissor.ROCK);
    }

    public GameService() {
    }

    // Find the winner in a RockPaperScissors game and return the game updated with winner.
    public RockPaperScissorsGame calculateWinner(RockPaperScissorsGame game) throws NoWinnerException {
        RockPaperScissor player1Move = game.getPlayer1().getMove();
        RockPaperScissor player2Move = game.getPlayer2().getMove();

        // Check if game is a draw -> no winner
        if (Objects.equals(player1Move, player2Move)) {
            // no winner, game is a draw
            throw new NoWinnerException("No winner - the game is a draw.");
        }
        // check if player 1 has a winning move
        else if (Objects.equals(player2Move, winningMoves.get(player1Move))) {
            game.setWinner(game.getPlayer1());
        }
        // check if player 2 has a winning move
        else if (Objects.equals(player1Move, winningMoves.get(player2Move))) {
            game.setWinner(game.getPlayer2());
        }
        return game;
    }
}

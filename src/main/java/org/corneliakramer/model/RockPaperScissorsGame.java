package org.corneliakramer.model;

/**
 * @author Cornelia Kramer Karlsson
 * Model class for a game of RockPaperScissors.
 * @see {@link Player}, {@link RockPaperScissor}
 */
public class RockPaperScissorsGame {
    private Player player1;
    private Player player2;
    private Player winner;

    public RockPaperScissorsGame() {
        this.winner = null;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }
}

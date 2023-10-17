package org.corneliakramer.model;

import jakarta.validation.constraints.*;

/**
 * @author Cornelia Kramer Karlsson
 * Model class for a player of {@link RockPaperScissorsGame}.
 */
public class Player {
    @NotBlank(message = "Player name must be set")
    @Size(max=25, message="Player name can't be longer than 25 characters")
    @Pattern(regexp = "^[a-zA-Z0-9_.-]*$", message="Player name can only contain letters (A-Z, a-z), numbers, dot, dash and underscore) ")
    private String playerName;

    @NotNull(message = "Player must select a move")
    private RockPaperScissor move;

    public Player() {
    }

    public Player(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public RockPaperScissor getMove() {
        return move;
    }

    public void setMove(RockPaperScissor move) {
        this.move = move;
    }
}

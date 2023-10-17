package org.corneliakramer.exception;

/**
 * @author Cornelia Kramer Karlsson
 * Custom exception used when a {@link org.corneliakramer.model.RockPaperScissorsGame} has no winner.
 */
public class NoWinnerException extends Exception {
    public NoWinnerException(String errorMessage) {
        super(errorMessage);
    }
}

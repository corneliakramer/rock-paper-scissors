package org.corneliakramer.service;

import org.corneliakramer.exception.NoWinnerException;
import org.corneliakramer.model.Player;
import org.corneliakramer.model.RockPaperScissor;
import org.corneliakramer.model.RockPaperScissorsGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class GameServiceTests {

    private RockPaperScissorsGame game;

    @InjectMocks
    GameService service;

    @BeforeEach
    public void init() {
        game = new RockPaperScissorsGame();
        game.setPlayer1(new Player("player1"));
        game.setPlayer2(new Player("player2"));
    }

    @Test
    public void GameService_RockBeatsScissors() throws NoWinnerException {
        // given
        game.getPlayer1().setMove(RockPaperScissor.ROCK);
        game.getPlayer2().setMove(RockPaperScissor.SCISSORS);
        //when
        service.calculateWinner(game);
        //then
        assertThat(game.getWinner()).isSameAs(game.getPlayer1());
    }

    @Test
    public void GameService_ScissorsBeatsPaper() throws NoWinnerException {
        // given
        game.getPlayer1().setMove(RockPaperScissor.SCISSORS);
        game.getPlayer2().setMove(RockPaperScissor.PAPER);
        //when
        service.calculateWinner(game);
        //then
        assertThat(game.getWinner()).isSameAs(game.getPlayer1());
    }

    @Test
    public void GameService_PaperBeatsRock() throws NoWinnerException {
        // given
        game.getPlayer1().setMove(RockPaperScissor.PAPER);
        game.getPlayer2().setMove(RockPaperScissor.ROCK);
        //when
        service.calculateWinner(game);
        //then
        assertThat(game.getWinner()).isSameAs(game.getPlayer1());
    }

    @Test
    public void GameService_Player1WinningMove_SetsPlayer1AsWinner() throws NoWinnerException {
        // given
        game.getPlayer1().setMove(RockPaperScissor.ROCK);
        game.getPlayer2().setMove(RockPaperScissor.SCISSORS);
        //when
        service.calculateWinner(game);
        //then
        assertThat(game.getWinner()).isSameAs(game.getPlayer1());
    }

    @Test
    public void GameService_Player2WinningMove_SetsPlayer2AsWinner() throws NoWinnerException {
        // given
        game.getPlayer1().setMove(RockPaperScissor.SCISSORS);
        game.getPlayer2().setMove(RockPaperScissor.ROCK);
        //when
        service.calculateWinner(game);
        //then
        assertThat(game.getWinner()).isSameAs(game.getPlayer2());
    }

    @Test
    public void GameService_EqualMoves_ThrowsNoWinnerException() {
        // given
        game.getPlayer1().setMove(RockPaperScissor.ROCK);
        game.getPlayer2().setMove(RockPaperScissor.ROCK);
        // then
        assertThrows(NoWinnerException.class, () -> {
            service.calculateWinner(game);
        });
    }
}

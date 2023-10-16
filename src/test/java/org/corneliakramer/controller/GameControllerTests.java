package org.corneliakramer.controller;

import org.corneliakramer.model.Player;
import org.corneliakramer.model.RockPaperScissor;
import org.corneliakramer.model.RockPaperScissorsGame;
import org.corneliakramer.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@WebMvcTest(controllers = GameController.class)
@ExtendWith(MockitoExtension.class)
public class GameControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private GameController gameController;
    @MockBean
    private GameService gameService;
    @MockBean
    private RockPaperScissorsGame game;

    @BeforeEach
    public void init() {
        game = new RockPaperScissorsGame();
    }

    @Test
    public void GameController_RootCalled_ReturnIndexView() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/game"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"));
    }

    @Test
    public void GameController_NewGame_ReturnPlayer1FormView() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/game/play"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("player1"))
                .andExpect(model().attributeExists("player1"));
        assertThat(game).isNotNull();
    }

    @Test
    public void GameController_SubmitPlayer1Form_ReturnPlayer2FormView() throws Exception {
        // setup
        Player testPlayer1 = new Player("player1");
        testPlayer1.setMove(RockPaperScissor.ROCK);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/game/play"));

        // test
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/game/play")
                                .param("playerName", testPlayer1.getPlayerName())
                                .param("move", testPlayer1.getMove().toString())
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("player2"))
                .andExpect(model().attributeExists("player2"));
    }
}

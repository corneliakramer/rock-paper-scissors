package org.corneliakramer.controller;

import org.corneliakramer.exception.NoWinnerException;
import org.corneliakramer.model.Player;
import org.corneliakramer.model.RockPaperScissorsGame;
import org.corneliakramer.service.GameService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Cornelia Kramer Karlsson
 * Controller class that exposes the application views for a game of RockPaperScissors.
 * @see {@link RockPaperScissorsGame}, {@link GameService}
 */
@Controller
@RequestMapping("/game")
public class GameController {
    @Autowired
    private GameService gameService;
    private RockPaperScissorsGame game;

    /**
     * @return view for index page
     */
    @GetMapping()
    public String index() {
        return "index";
    }

    /**
     * Call (GET) to start new game.
     * @param model - used by view to set player details from user input.
     * @return view with player1 form.
     */
    @GetMapping("/play")
    public String player1Form(Model model) {
        this.game = new RockPaperScissorsGame();
        model.addAttribute("player1", new Player());
        return "player1";
    }

    /**
     * Call (POST) to submit player 1 details for ongoing game.
     * Must be preceded by call to start new game {@link #player1Form}.
     * @param player1 - {@code Player} object with details for player 1 in the game.
     * @param model - used by view to set player details from user input.
     * @return view with player 2 form.
     */
    @PostMapping("/play")
    public String player1Submit(@Valid @ModelAttribute Player player1, Model model) {
        this.game.setPlayer1(player1);
        model.addAttribute("player2", new Player());
        return "player2";
    }

    /**
     * Call (POST) to submit player 2 details for ongoing game and finish it.
     * Must be preceded by (1) call to start new game {@link #player1Form}
     * and (2) call to submit player1 details {@link #player1Submit}.
     * @param player2 - {@code Player} object with details for player 2 in the game.
     * @param model - used by view to set player details from user input.
     * @return view with the result of the game, or an error page if regular exceptions occur.
     * @see {@code Player} for validation requirements.
     */
    @PostMapping("/result")
    public String player2Submit(@Valid @ModelAttribute Player player2, Model model) {
        this.game.setPlayer2(player2);
        //Service call to process game
        try {
            //gameService calculates and sets winner of game
            gameService.calculateWinner(game);
            model.addAttribute("game", game);
            return "result";
        }
        catch(NoWinnerException e) {
            model.addAttribute("game", game);
            return "no-winner";
        }
        catch(Exception e) {
            model.addAttribute("error", e.toString());
            return "error";
        }
    }
}

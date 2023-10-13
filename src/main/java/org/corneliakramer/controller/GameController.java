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

@Controller
@RequestMapping("/game")
public class GameController {
    @Autowired
    private GameService gameService;
    private RockPaperScissorsGame game;

    @GetMapping()
    public String index() {
        return "index";
    }

    @GetMapping("/play")
    public String player1Form(Model model) {
        this.game = new RockPaperScissorsGame();
        model.addAttribute("player1", new Player());
        return "player1";
    }

    @PostMapping("/play")
    public String player1Submit(@Valid @ModelAttribute Player player1, Model model) {
        this.game.setPlayer1(player1);
        model.addAttribute("player2", new Player());
        return "player2";
    }

    @PostMapping("/result")
    public String player2Submit(@Valid @ModelAttribute Player player2, Model model) {
        this.game.setPlayer2(player2);
        //Service call to process game
        try {
            game = gameService.calculateWinner(game);
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

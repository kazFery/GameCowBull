package com.game.controller;


import com.game.models.Game;
import com.game.models.Round;
import com.game.service.GameService;
import com.game.service.InvalidGuessNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class GameController {

    @Autowired
    private GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/start")
    @ResponseStatus(HttpStatus.CREATED)
    public int create() {
        return gameService.addGame();
    }

    @GetMapping("/game")
    public List<Game> getAllGame(){
        return gameService.getAllGames();
    }

    @PostMapping("/guess")
    public Round getGuess(@RequestBody Round round) throws InvalidGuessNumber {
        return gameService.addRound(round);
    }

    @GetMapping("game/{id}")
    public Game getGameById(@PathVariable int id){
        return gameService.getGameById(id);
    }


    @GetMapping("rounds/{id}")
    public List<Round> getAllRoundsByGameId(@PathVariable int id){
        return gameService.getAllRoundsByGameId(id);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity update(@PathVariable int id, @RequestBody Game game) {
//        ResponseEntity response = new ResponseEntity(HttpStatus.NOT_FOUND);
//        if (id != game.getId()) {
//            response = new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
//        } else if (gameDao.updateGame(game)) {
//            response = new ResponseEntity(HttpStatus.NO_CONTENT);
//        }
//        return response;
//
//    }

}

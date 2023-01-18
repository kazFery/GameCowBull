package com.game.service;

import com.game.data.GameDao;
import com.game.data.RoundDao;
import com.game.models.Game;
import com.game.models.Round;

import java.util.List;

public interface GameService {

   int addGame();
   //int updateGame(Game game);
   List<Game> getAllGames();
   Game getGameById(int id);
   Round addRound(Round round) throws InvalidGuessNumber;
   List<Round> getAllRoundsByGameId(int id);




}

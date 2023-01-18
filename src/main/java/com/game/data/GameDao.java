package com.game.data;

import com.game.models.Game;
import com.game.models.Round;

import java.util.List;

public interface GameDao {

    int addGame(Game game);

    boolean updateGame(Game game);

    Game getGameById(int gameId);

    Game getGameAnswerById(int gameId);

    List<Game> getAllGames();








}

package com.game.data;

import com.game.models.Game;
import com.game.models.GameStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.util.Streams.stream;

public class GameDaoStubImpl implements GameDao{

    private List<Game> games = new ArrayList<>();

    @Override
    public int addGame(Game game) {
        game.setId(games.size() + 1);
        games.add(game);
        return games.indexOf(game);
    }

    @Override
    public boolean updateGame(Game game) {
        game.setGameStatus(GameStatus.FINISHED);
        return true;
    }

    @Override
    public Game getGameById(int gameId) {
        Game result = null;
        for (Game g: games
             ) {
             if (g.getId() == gameId){
                 result = g;
                 break;
             }

        }
        return result;
    }

    @Override
    public Game getGameAnswerById(int gameId) {
        Game result = null;
        for (Game g: games
        ) {
            if (g.getId() == gameId){
                result = g;
                break;
            }

        }
        return result;
    }

    @Override
    public List<Game> getAllGames() {
        return games;
    }
}

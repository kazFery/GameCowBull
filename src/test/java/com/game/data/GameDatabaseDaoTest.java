package com.game.data;

import com.game.TestApplicationConfiguration;
import com.game.models.Game;
import com.game.models.GameStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)

class GameDatabaseDaoTest {
    @Autowired
    GameDao  gameDao;

    @Test
    void add() {

        Game game1 = new Game();
        game1.setGameStatus(GameStatus.INPROGRESS);
        game1.setAnswer("1234");
        gameDao.addGame(game1);

        Game game1FromDao =gameDao.getGameAnswerById(game1.getId());
        assertEquals(game1, game1FromDao);

        Game game2 = new Game();
        game2.setGameStatus(GameStatus.INPROGRESS);
        game2.setAnswer("1538");
        gameDao.addGame(game2);

        Game game2FromDao =gameDao.getGameAnswerById(game2.getId());
        assertEquals(game2, game2FromDao);

    }

    @Test
    void getAll() {

        List<Game> games = gameDao.getAllGames();

        assertEquals(2, games.size());

    }

}
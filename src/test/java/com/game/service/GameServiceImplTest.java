package com.game.service;

import com.game.data.GameDao;
import com.game.data.GameDaoStubImpl;
import com.game.data.RoundDao;
import com.game.data.RoundDaoStubImpl;
import com.game.models.Game;
import com.game.models.GameStatus;
import com.game.models.Round;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameServiceImplTest {

    private GameService service;

    public GameServiceImplTest() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        service = applicationContext.getBean(com.game.service.GameServiceImpl.class);

    }
    @Test
    void addGame() {

        int sizeBeforeAdd = service.getAllGames().size();
        service.addGame();
        int sizeAfterAdd=service.getAllGames().size();
        assertEquals(sizeBeforeAdd,sizeAfterAdd-1);

    }

    @Test
    void  getAllGameTest() {
         service.addGame();
         service.addGame();
         List<Game> games = service.getAllGames();
         assertEquals(2, games.size() );

    }
    @Test
    void  getGameByIdtest(){
        service.addGame();
        service.addGame();
        Game game1 = service.getGameById(1);
        assertEquals(game1.getGameStatus() , GameStatus.INPROGRESS);
    }

    @Test
    void adRoundTest() throws InvalidGuessNumber {
        service.addGame();
        service.addGame();
        Round round = new Round();
        round.setGuess("4589");
        round.setGameId(1);
        Round newRound = service.addRound(round);
        Game  firstGame= service.getGameById(1);
        assertNotEquals(newRound.getGuess(), firstGame.getAnswer());
    }
    @Test
    void getAllRoundsByGameIdTest() throws InvalidGuessNumber {
        service.addGame();
        Round round = new Round();
        round.setGuess("4589");
        round.setGameId(1);
       service.addRound(round);

        Round round2 = new Round();
        round2.setGuess("1589");
        round2.setGameId(1);
        service.addRound(round2);

        List<Round> rounds = service.getAllRoundsByGameId(1);

        assertEquals(rounds.size() , 2);



    }





}
package com.game.data;

import com.game.TestApplicationConfiguration;
import com.game.models.Game;
import com.game.models.GameStatus;
import com.game.models.Round;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
class RoundDatabaseDaoTest {
    @Autowired
    RoundDao  roundDao;

    @Test
    void add() {

        Round round1 = new Round();
        round1.setGameId(1);
        round1.setGuess("3476");
        round1.setGameTime(LocalDateTime.now());
        round1.setResult("e:0:p:1");
        roundDao.addRound(round1);
        List<Round>  rounds= roundDao.getAllRoundsByGameId(1);
        assertEquals(2,rounds.size());


    }

    @Test
    void getAll() {

        List<Round> rounds = roundDao.getAllRoundsByGameId(1);

        assertEquals(2, rounds.size());

    }


}
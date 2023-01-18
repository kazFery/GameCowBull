package com.game.service;

import com.game.data.GameDao;
import com.game.data.RoundDao;
import com.game.models.Game;
import com.game.models.GameStatus;
import com.game.models.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class GameServiceImpl implements GameService{
    @Autowired
    private  GameDao gameDao;

    @Autowired
    private RoundDao roundDao;

    public GameServiceImpl(GameDao gameDao, RoundDao roundDao) {
        this.gameDao = gameDao;
        this.roundDao = roundDao;
    }

    List<String> allAnswer = new ArrayList<String>();

    @Override
    public int addGame() {
        Game newGame = new Game();
        newGame.setAnswer(getANum());
        newGame.setGameStatus(GameStatus.INPROGRESS);
        return gameDao.addGame(newGame);
    }



    @Override
    public List<Game> getAllGames() {
        return  gameDao.getAllGames() ;

    }

    @Override
    public Game getGameById(int id) {
        return  gameDao.getGameById(id) ;

    }

    @Override
    public Round addRound(Round round) throws InvalidGuessNumber {
        if (validateInputGuess(round.getGuess())) {
            int gameId = round.getGameId();
            Game currentGame = gameDao.getGameAnswerById(gameId);
            round.setResult(compairGuessAnswer(currentGame.getAnswer(), round.getGuess()));
            round.setGameTime(LocalDateTime.now());
            if (checkBulls(round.getResult())) {
                currentGame.setGameStatus(GameStatus.FINISHED);
                gameDao.updateGame(currentGame);
            }
            return roundDao.addRound(round);

        }
        else
            return round;
    }

    @Override
    public List<Round> getAllRoundsByGameId(int gameId) {

        return roundDao.getAllRoundsByGameId(gameId);
    }

    private String getANum() {
        for (int a = 1; a <= 9; a++) {
            for (int b = 1; b <= 9; b++) {
                if (b == a) continue;
                for (int c = 1; c <= 9; c++) {
                    if (c == b || c == a) continue;
                    for (int d = 1; d <= 9; d++) {
                        if (d == a || d == b || d == c) continue;
                        String cnt = ""+a+b+c+d;
                        allAnswer.add(cnt);
                    }
                }
            }
        }
        String aCase = allAnswer.get((int)(Math.random() * allAnswer.size()));
        return aCase;
    }

    static boolean validateInputGuess(String guess) throws InvalidGuessNumber {
        Matcher  match =  Pattern.compile("^[1-9]{4}$").matcher(guess);
        if (guess.length() != 4  || !match.matches())  // if a digits
            throw new InvalidGuessNumber(" Guess number should be 4 digits");
        return true;
    }

    private String  compairGuessAnswer(String ans,String guess) {
        int bulls = 0, cows = 0;  // bulls means exact match, cows means partial match
        for (int i = 0; i < guess.length(); i++) {
            int ind = ans.indexOf(guess.charAt(i));
            if (ind == i) bulls++;
            else if (ind >= 0) cows++;
        }
        // result format e:0:p:0
        String result =  "e:" + bulls+ ":p:" + cows;
        return  result;
    }

    private boolean checkBulls(String result){
       if ( Integer.parseInt(String.valueOf(result.charAt(2))) == 4)
           return true;
       return false;
    }


}
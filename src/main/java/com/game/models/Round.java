package com.game.models;



import java.time.LocalDateTime;

public class Round {
    private int roundId;
    private int gameId;
    private String guess;
    private String result;
    private LocalDateTime gameTime;

    public Round() {
    }

    public Round(int roundId, int gameId, String guess, String result, LocalDateTime gameTime) {
        this.roundId = roundId;
        this.gameId = gameId;
        this.guess = guess;
        this.result = result;
        this.gameTime = gameTime;
    }

    public Round(int gameId, String guess) {
        this.gameId = gameId;
        this.guess = guess;
    }

    public int getRoundId() {
        return roundId;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public LocalDateTime getGameTime() {
        return gameTime;
    }

    public void setGameTime(LocalDateTime gameTime) {
        this.gameTime = gameTime;
    }


}

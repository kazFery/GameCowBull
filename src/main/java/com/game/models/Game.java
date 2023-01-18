package com.game.models;

import java.util.Objects;

public class Game {
    private int id;
    private  String answer;
    private GameStatus gameStatus;  // true means finished and false means in progress

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return this.id == game.id &&  this.answer.equals(game.answer) && this.gameStatus.equals(game.gameStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, answer, gameStatus);
    }
}

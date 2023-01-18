package com.game.service;

public class InvalidGuessNumber extends Exception{
    public InvalidGuessNumber(String message) {
        super(message);
    }

    public InvalidGuessNumber(String message, Throwable cause) {
        super(message, cause);
    }
}

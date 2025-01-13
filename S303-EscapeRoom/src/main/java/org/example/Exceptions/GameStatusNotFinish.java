package org.example.Exceptions;

public class GameStatusNotFinish extends RuntimeException {
    public GameStatusNotFinish(String message) {
        super(message);
    }
}

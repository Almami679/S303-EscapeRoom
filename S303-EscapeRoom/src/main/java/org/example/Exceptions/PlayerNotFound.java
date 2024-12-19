package org.example.Exceptions;

public class PlayerNotFound extends RuntimeException {
    public PlayerNotFound(String message) {
        super(message);
    }
}

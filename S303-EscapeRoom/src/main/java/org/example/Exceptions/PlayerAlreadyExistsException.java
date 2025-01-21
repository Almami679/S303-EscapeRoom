package org.example.Exceptions;

public class PlayerAlreadyExistsException extends RuntimeException{
    public PlayerAlreadyExistsException() {
        super("Player already exists");
    }
}

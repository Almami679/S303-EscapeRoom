package org.example.Exceptions;

public class GiftNotFoundException extends RuntimeException{
    public GiftNotFoundException(String message) {
        super(message);
    }
}

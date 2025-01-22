package org.example.Exceptions;

public class EscapeRoomNotFoundException extends RuntimeException {
    public EscapeRoomNotFoundException() {
        super("EscapeRoom not found");
    }
}

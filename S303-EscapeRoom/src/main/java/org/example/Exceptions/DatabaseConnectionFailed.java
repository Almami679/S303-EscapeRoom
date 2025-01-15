package org.example.Exceptions;

public class DatabaseConnectionFailed extends RuntimeException {
    public DatabaseConnectionFailed(String message) {
        super(message);
    }
}

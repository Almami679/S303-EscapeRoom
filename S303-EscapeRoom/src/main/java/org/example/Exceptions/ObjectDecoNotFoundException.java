package org.example.Exceptions;

public class ObjectDecoNotFoundException extends RuntimeException {
    public ObjectDecoNotFoundException() {
        super("Decoration not found");
    }
}

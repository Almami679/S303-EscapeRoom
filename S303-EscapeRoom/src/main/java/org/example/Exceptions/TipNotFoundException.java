package org.example.Exceptions;

public class TipNotFoundException extends RuntimeException {
    public TipNotFoundException() {
        super("Tip not found");
    }
}

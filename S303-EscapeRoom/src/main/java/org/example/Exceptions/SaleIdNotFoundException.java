package org.example.Exceptions;

public class SaleIdNotFoundException extends RuntimeException{
    public SaleIdNotFoundException() {
        super("Sale not found");
    }
}

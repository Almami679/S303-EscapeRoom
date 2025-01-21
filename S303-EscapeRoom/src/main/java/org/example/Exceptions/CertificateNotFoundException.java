package org.example.Exceptions;

public class CertificateNotFoundException extends RuntimeException{
    public CertificateNotFoundException(String message) {
        super(message);
    }
}

package com.acme.edu.exceptions;

public class FlushException extends LogException {
    public FlushException(String message) {
        super("Flush Exception: " + message);
    }
}

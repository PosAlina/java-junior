package com.acme.edu.exceptions;

public class CharCommandException extends LogException {
    public CharCommandException() {
        super("CharCommandException");
    }

    public CharCommandException(String s) {
        super(s);
    }
}

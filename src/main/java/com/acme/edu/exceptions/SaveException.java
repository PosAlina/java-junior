package com.acme.edu.exceptions;

public class SaveException extends LogException {
    public SaveException(String message) { super("Saver:" + message); }
}

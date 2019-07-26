package com.acme.edu.command;

import com.acme.edu.exceptions.CharCommandException;
import com.acme.edu.exceptions.LogException;

public class CharCommand extends Command{
    private char message;

    public CharCommand(char message) {
        updateMessage(message);
        prefix = "char: ";
    }

    public CharCommand(String message) throws CharCommandException {
        this(message.charAt(0));
        if (message.length() != 1) {
            throw new CharCommandException("Tried to create charcommand with multiple symbols");
        }
    }

    private void updateMessage(char message) {
        this.message = message;
        messageAsString = String.valueOf(message);
    }
}
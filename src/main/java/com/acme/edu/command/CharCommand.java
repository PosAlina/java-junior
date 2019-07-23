package com.acme.edu.command;

public class CharCommand extends Command{
    private char message;

    public CharCommand(char message) {
        updateMessage(message);
        state = States.CHAR_STATE;
    }

    private void updateMessage(char message) {
        this.message = message;
        messageAsString = String.valueOf(message);
    }
}
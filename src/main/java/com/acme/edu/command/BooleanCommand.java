package com.acme.edu.command;

public class BooleanCommand extends Command {
    private boolean message;

    public BooleanCommand(boolean message) {
        updateMessage(message);
        state = States.BOOLEAN_STATE;
    }

    private void updateMessage(boolean message) {
        this.message = message;
        messageAsString = String.valueOf(message);
    }
}
package com.acme.edu.command;

public class BooleanCommand extends Command {
    private boolean message;

    public BooleanCommand(boolean message) {
        updateMessage(message);
        prefix = "primitive: ";
    }

    private void updateMessage(boolean message) {
        this.message = message;
        messageAsString = String.valueOf(message);
    }
}
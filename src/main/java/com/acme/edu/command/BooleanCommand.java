package com.acme.edu.command;

public class BooleanCommand extends Command {
    private boolean message;

    public BooleanCommand(boolean message) {
        updateMessage(message);
        prefix = "primitive: ";
    }

    public BooleanCommand(String message) {
        this(Boolean.parseBoolean(message));
    }

    private void updateMessage(boolean message) {
        this.message = message;
        messageAsString = String.valueOf(message);
    }
}
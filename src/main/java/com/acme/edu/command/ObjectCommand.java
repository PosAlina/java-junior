package com.acme.edu.command;

public class ObjectCommand extends Command {
    private Object message;

    public ObjectCommand(Object message) {
        updateMessage(message);
        state = States.REFERENCE_STATE;
    }

    private void updateMessage(Object message) {
        this.message = message;
        messageAsString = String.valueOf(message);
    }
}
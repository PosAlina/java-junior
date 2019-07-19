package com.acme.edu.command;

public class ObjectCommand extends Command {
    private Object message;

    public ObjectCommand(Object message) {
        this.message = message;
        this.state = States.REFERENCE_STATE;
    }

    @Override
    public String getMessageAsString() {
        return String.valueOf(message);
    }
}

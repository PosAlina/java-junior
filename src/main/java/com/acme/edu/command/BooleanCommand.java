package com.acme.edu.command;

public class BooleanCommand extends Command {
    private boolean message;

    @Override
    public String getMessageAsString() {
        return String.valueOf(message);
    }

    public BooleanCommand(boolean message) {
        this.message = message;
        this.state = States.BOOLEAN_STATE;
    }
}

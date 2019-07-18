package com.acme.edu.command;

public class BooleanCommand extends Command{
    private boolean message;

    public BooleanCommand(boolean message) {
        this.message = message;
        this.state = States.BOOLEAN_STATE;
    }

    @Override
    public String getStringMessage() {
        return String.valueOf(message);
    }

    @Override
    public void accumulate(Command secondCommand) {
        isToBeSaved = true;
    }
}

package com.acme.edu.command;

public class ObjectCommand extends Command{
    private Object message;

    public ObjectCommand(Object message){
        this.message = message;
        this.state = States.REFERENCE_STATE;
    }

    public String getStringMessage() {
        return String.valueOf(message);
    }

    @Override
    public void accumulate(Command secondCommand) {
        isToBeSaved = true;
    }
}

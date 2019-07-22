package com.acme.edu.command;

import java.util.Objects;

public class StringCommand extends Command {
    private String message;
    private int stringCounter = 1;

    public StringCommand(String message) {
        this.message = message;
        this.state = States.STRING_STATE;
    }

    @Override
    public void accumulate(Command secondCommand) {
        isToBeSaved = !isEqualStringCommand(secondCommand);
        if (!isToBeSaved) {
            stringCounter++;
        }
    }

    @Override
    public String getMessageAsString() {
        if (stringCounter > 1) {
            return message + " (x" + stringCounter + ")";
        }
        return message;
    }

    public String getMessage() {
        return message;
    }

    public int getStringCounter() {
        return stringCounter;
    }

    private boolean isEqualStringCommand(Command secondCommand) {
        return secondCommand instanceof StringCommand && Objects.equals(message, ((StringCommand) secondCommand).getMessage());
    }
}

package com.acme.edu.command;

import java.util.Objects;

public class StringCommand extends Command{
    private String message;
    private int stringCounter = 1;

    public StringCommand(String message) {
        this.message = message;
        this.state = States.STRING_STATE;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public void accumulate(Command secondCommand) {
        if (secondCommand instanceof StringCommand && Objects.equals(message, ((StringCommand)secondCommand).getMessage())) {
            stringCounter++;
            isToBeSaved = false;
        } else {
            isToBeSaved = true;
        }
    }

    @Override
    public String getStringMessage() {
        StringBuilder newMessage = new StringBuilder(message);
        if (stringCounter > 1) {
           newMessage.append(" (x" + stringCounter + ")");
        }
        return newMessage.toString();
    }
}

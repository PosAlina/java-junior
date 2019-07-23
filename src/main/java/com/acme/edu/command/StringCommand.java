package com.acme.edu.command;

import java.util.Objects;

public class StringCommand extends Command {
    private String message;
    private int stringCounter;

    public StringCommand(String message) {
        updateMessage(message);
        state = States.STRING_STATE;
        stringCounter = 1;
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
        if (stringCounter == 1) { return message; }
        return message + " (x" + stringCounter + ")";
    }

    public String getMessage() {
        return message;
    }

    public int getStringCounter() {
        return stringCounter;
    }

    private boolean isEqualStringCommand(Command currentCommand) {
        return isEqualTypes(currentCommand) && isEqualMessages((StringCommand) currentCommand);
    }

    private boolean isEqualTypes(Command message) {
        return message instanceof StringCommand;
    }

    private boolean isEqualMessages(StringCommand currentCommand) {
        return Objects.equals(message, currentCommand.getMessage());
    }

    private void updateMessage(String message) {
        this.message = message;
        messageAsString = message;
    }
}
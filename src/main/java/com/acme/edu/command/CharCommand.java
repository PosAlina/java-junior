package com.acme.edu.command;

public class CharCommand extends Command{
    public char message;

    public CharCommand(char message) {
        this.message = message;
        this.state = States.CHAR_STATE;
    }

    @Override
    public String getMessageAsString() {
        return String.valueOf(message);
    }
}

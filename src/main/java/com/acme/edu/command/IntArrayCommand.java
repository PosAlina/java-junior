package com.acme.edu.command;

public class IntArrayCommand extends Command {
    private int[] message;

    public IntArrayCommand(int[] message) {
        this.message = message;
        this.state = States.INTARRAY_STATE;
    }

    @Override
    public String getMessageAsString() {
        if (message.length == 0) {
            return "{}";
        }
        StringBuilder decoratedMessage = new StringBuilder("{");
        int index = 0;
        while (index < message.length - 1) {
            decoratedMessage.append(message[index]).append(", ");
            index++;
        }
        decoratedMessage.append(message[index]).append("}");
        return decoratedMessage.toString();
    }
}

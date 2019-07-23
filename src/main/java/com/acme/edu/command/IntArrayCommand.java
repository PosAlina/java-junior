package com.acme.edu.command;

public class IntArrayCommand extends Command {
    private int[] message;

    public IntArrayCommand(int[] message) {
        updateMessage(message);
        this.state = States.INTARRAY_STATE;
    }

    private String createMessageAsString() {
        if (message.length == 0) { return "{}"; }
        StringBuilder createdMessageAsString = new StringBuilder("{");
        int arrayIndex = 0;
        while (arrayIndex < message.length - 1) {
            createdMessageAsString.append(message[arrayIndex])
                    .append(", ");
            arrayIndex++;
        }
        createdMessageAsString.append(message[arrayIndex])
                .append("}");
        return createdMessageAsString.toString();
    }

    private void updateMessage(int[] message) {
        this.message = message;
        messageAsString = createMessageAsString();
    }
}
package com.acme.edu.command;

public class IntMultiMatrixCommand extends Command {
    private Object message;

    public IntMultiMatrixCommand(Object message) {
        updateMessage(message);
        state = States.INTMULTIMATRIX_STATE;
    }

    private String createMessageAsString(Object message) {
        if (!isArray(message)) { return arrayProcess((int[]) message); }
        String newLine = System.lineSeparator();
        StringBuilder createdMessageAsString = new StringBuilder("{");
        createdMessageAsString.append(newLine);
        for (Object arrayElement : (Object[]) message) {
            createdMessageAsString.append(createMessageAsString(arrayElement));
        }
        createdMessageAsString.append("}")
                .append(newLine);
        return createdMessageAsString.toString();
    }

    private boolean isArray(Object message) {
        return message instanceof Object[];
    }

    private String arrayProcess(int[] line) {
        String newLine = System.lineSeparator();
        IntArrayCommand array = new IntArrayCommand(line);
        return array.getMessageAsString() + newLine;
    }

    private void updateMessage(Object message) {
        this.message = message;
        messageAsString = createMessageAsString(message);
    }
}
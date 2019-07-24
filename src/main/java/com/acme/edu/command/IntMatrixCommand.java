package com.acme.edu.command;

public class IntMatrixCommand extends Command {
    private int[][] message;

    public IntMatrixCommand(int[][] message) {
        updateMessage(message);
        prefix = "primitives matrix: ";
    }

    private String createMessageAsString() {
        if (message.length == 0) { return "{}"; }
        String newLine = System.lineSeparator();
        StringBuilder createdMessageAsString = new StringBuilder("{");
        createdMessageAsString.append(newLine);
        for (int[] matrixLine : message) {
            createdMessageAsString.append(arrayProcess(matrixLine))
                    .append(newLine);
        }
        createdMessageAsString.append("}")
                .append(newLine);
        return createdMessageAsString.toString();
    }

    private String arrayProcess(int[] matrixLine) {
        IntArrayCommand array = new IntArrayCommand(matrixLine);
        return array.getMessageAsString();
    }

    private void updateMessage(int[][] message) {
        this.message = message;
        messageAsString = createMessageAsString();
    }
}
package com.acme.edu.command;

public class IntMatrixCommand extends Command {
    private int[][] message;

    public IntMatrixCommand(int[][] message) {
        this.message = message;
        this.state = States.INTMATRIX_STATE;
    }

    @Override
    public String getMessageAsString() {
        String newLine = System.lineSeparator();
        if (message.length == 0) {
            return "{}";
        }
        StringBuilder decoratedMessage = new StringBuilder("{");
        decoratedMessage.append(newLine);
        for (int[] line : message) {
            decoratedMessage.append(arrayProcess(line));
            decoratedMessage.append(newLine);
        }
        decoratedMessage.append("}").append(newLine);
        return decoratedMessage.toString();
    }

    private String arrayProcess(int[] line) {
        IntArrayCommand array = new IntArrayCommand(line);
        return array.getMessageAsString();
    }
}
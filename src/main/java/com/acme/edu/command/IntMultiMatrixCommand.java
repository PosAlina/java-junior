package com.acme.edu.command;

public class IntMultiMatrixCommand extends Command {
    private Object message;

    public IntMultiMatrixCommand(Object message) {
        this.message = message;
        this.state = States.INTMULTIMATRIX_STATE;
    }

    @Override
    public String getMessageAsString() {
        return multiMatrixDecorate(message);
    }

    private String multiMatrixDecorate(Object message) {
        String newLine = System.lineSeparator();
        StringBuilder decoratedMessage = new StringBuilder();
        if (message instanceof Object[]) {
            decoratedMessage.append("{").append(newLine);
            for (Object array : (Object[]) message) {
                decoratedMessage.append(multiMatrixDecorate(array));
            }
            return decoratedMessage + "}" + newLine;
        }
        return decoratedMessage + arrayProcess((int[]) message);
    }

    private String arrayProcess(int[] line) {
        String newLine = System.lineSeparator();
        IntArrayCommand array = new IntArrayCommand(line);
        return array.getMessageAsString() + newLine;
    }
}

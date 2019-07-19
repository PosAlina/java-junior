package com.acme.edu.command;

public class ByteCommand extends Command {
    private int message;
    private int overflowRest = 0;

    public ByteCommand(byte message) {
        this.message = message;
        this.state = States.BYTE_STATE;
    }

    @Override
    public String getMessageAsString() {
        return String.valueOf(message);
    }

    @Override
    public void update() {
        isToBeSaved = false;
        isToFixOverflow = false;
        message = overflowRest;
        overflowRest = 0;
    }

    @Override
    public void accumulate(Command secondCommand) {
        if (secondCommand instanceof ByteCommand) {
            isToFixOverflow = checkOverflow(((ByteCommand) secondCommand).getMessage());
            isToBeSaved = isToFixOverflow;
            if (!isToFixOverflow) {
                message = message + ((ByteCommand) secondCommand).getMessage();
            }
        } else {
            isToBeSaved = true;
        }
    }

    public int getMessage() {
        return message;
    }

    private boolean checkOverflow(int secondMessage) {
        if (isPositiveOverflow(secondMessage)) {
            return processOverflow(secondMessage, Byte.MAX_VALUE);
        } else if (isNegativeOverflow(secondMessage)) {
            return processOverflow(secondMessage, Byte.MIN_VALUE);
        }
        overflowRest = 0;
        return false;
    }

    private boolean processOverflow(int secondMessage, int overflowValue) {
        overflowRest = secondMessage - (overflowValue - message);
        message = overflowValue;
        return true;
    }

    private boolean isPositiveOverflow(int secondMessage) {
        return (secondMessage > 0) && (message > Byte.MAX_VALUE - secondMessage);
    }

    private boolean isNegativeOverflow(int secondMessage) {
        return (secondMessage < 0) && (message < Byte.MIN_VALUE - secondMessage);
    }
}

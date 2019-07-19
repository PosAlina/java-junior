package com.acme.edu.command;

public class IntCommand extends Command {
    private int message;
    private int overflowRest = 0;

    public IntCommand(int message) {
        this.message = message;
        this.state = States.INT_STATE;
    }

    @Override
    public void update() {
        isToBeSaved = false;
        isToFixOverflow = false;
        message = overflowRest;
        overflowRest = 0;
    }

    @Override
    public String getMessageAsString() {
        return String.valueOf(message);
    }

    @Override
    public void accumulate(Command secondCommand) {
        if (secondCommand instanceof IntCommand) {
            isToFixOverflow = detectedOverflow(((IntCommand) secondCommand).getMessage());
            isToBeSaved = isToFixOverflow;
            if (!isToFixOverflow) {
                message = message + ((IntCommand) secondCommand).getMessage();
            }
        } else {
            isToBeSaved = true;
        }
    }

    public int getMessage() {
        return message;
    }

    private boolean detectedOverflow(int secondMessage) {
        if (isPositiveOverflow(secondMessage)) {
            return processOverflow(secondMessage, Integer.MAX_VALUE);
        } else if (isNegativeOverflow(secondMessage)) {
            return processOverflow(secondMessage, Integer.MIN_VALUE);
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
        return (secondMessage > 0) && (message > Integer.MAX_VALUE - secondMessage);
    }

    private boolean isNegativeOverflow(int secondMessage) {
        return (secondMessage < 0) && (message < Integer.MIN_VALUE - secondMessage);
    }
}
package com.acme.edu.command;

import com.acme.edu.saver.Saver;

public class IntCommand extends Command {
    private int message;
    private int overflowRest;

    public IntCommand(int message) {
        updateMessage(message);
        state = States.INT_STATE;
        overflowRest = 0;
    }

    @Override
    public void accumulate(Command currentCommand) {
        if (!isEqualTypes(currentCommand)) {
            isToBeSaved = true;
            return;
        }
        int currentCommandMessage = ((IntCommand) currentCommand).getMessage();
        isToFixOverflow = checkAndProcessOverflow(currentCommandMessage);
        isToBeSaved = isToFixOverflow;
        if (!isToFixOverflow) {
            updateMessage(message + currentCommandMessage);
        }
    }

    @Override
    public void saveCommand(Saver saver) {
        if (!isToBeSaved) { return; }
        saver.save(decorate());
        if (isToFixOverflow) {
            update();
        }
    }

    public int getMessage() {
        return message;
    }

    public int getOverflowRest() {
        return overflowRest;
    }

    public void update() {
        isToBeSaved = false;
        isToFixOverflow = false;
        updateMessage(overflowRest);
        overflowRest = 0;
    }

    private boolean checkAndProcessOverflow(int secondMessage) {
        if (isPositiveOverflow(secondMessage)) {
            return processOverflow(secondMessage, Integer.MAX_VALUE);
        }
        if (isNegativeOverflow(secondMessage)) {
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
        return secondMessage > 0 && message > Integer.MAX_VALUE - secondMessage;
    }

    private boolean isNegativeOverflow(int secondMessage) {
        return secondMessage < 0 && message < Integer.MIN_VALUE - secondMessage;
    }

    private boolean isEqualTypes(Command message) {
        return message instanceof IntCommand;
    }

    private void updateMessage(int message) {
        this.message = message;
        messageAsString = String.valueOf(message);
    }
}
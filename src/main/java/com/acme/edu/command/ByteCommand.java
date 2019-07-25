package com.acme.edu.command;

import com.acme.edu.exceptions.SaveException;
import com.acme.edu.saver.Saver;

public class ByteCommand extends Command {
    private int message;
    private int overflowRest;

    public ByteCommand(byte message) {
        updateMessage(message);
        prefix = "primitive: ";
        overflowRest = 0;
    }

    @Override
    public void accumulate(Command currentCommand) {
        if (!isEqualTypes(currentCommand)) {
            isToBeSaved = true;
            return;
        }
        int currentCommandMessage = ((ByteCommand) currentCommand).getMessage();
        isToFixOverflow = checkAndProcessOverflow(currentCommandMessage);
        isToBeSaved = isToFixOverflow;
        if (!isToFixOverflow) {
            updateMessage(message + currentCommandMessage);
        }
    }

    @Override
    public void saveCommand(Saver saver) throws SaveException {
        if (saver == null) {
            throw new SaveException("Unexisting saver");
        }
        if (!isToBeSaved) { return; }
        saver.save(decorate());
        update();
    }

    public int getMessage() {
        return message;
    }

    public int getOverflowRest() {
        return overflowRest;
    }

    private void update() {
        if (!isToFixOverflow) { return; }
        updateMessage(overflowRest);
        overflowRest = 0;
        isToBeSaved = false;
        isToFixOverflow = false;
    }

    private boolean checkAndProcessOverflow(int secondMessage) {
        if (isPositiveOverflow(secondMessage)) {
            return processOverflow(secondMessage, Byte.MAX_VALUE);
        }
        if (isNegativeOverflow(secondMessage)) {
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
        return secondMessage > 0 && message > Byte.MAX_VALUE - secondMessage;
    }

    private boolean isNegativeOverflow(int secondMessage) {
        return secondMessage < 0 && message < Byte.MIN_VALUE - secondMessage;
    }

    private boolean isEqualTypes(Command message) {
        return message instanceof ByteCommand;
    }

    private void updateMessage(int message) {
        this.message = message;
        messageAsString = String.valueOf(message);
    }
}
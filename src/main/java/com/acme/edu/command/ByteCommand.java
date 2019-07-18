package com.acme.edu.command;

public class ByteCommand extends Command {
    private int message;
    private int overflowRest = 0;

    public ByteCommand(byte message) {
        this.message = message;
        this.state = States.BYTE_STATE;
    }

    public int getMessage() {
        return message;
    }

    private boolean checkOverflow(int secondMessage) {
        if ((secondMessage > 0) && (message > Byte.MAX_VALUE - secondMessage)) {
            overflowRest = secondMessage - (Byte.MAX_VALUE - message);
            message = Byte.MAX_VALUE;
            return true;
        } else if ((secondMessage < 0) && (message < Byte.MIN_VALUE - secondMessage)) {
            overflowRest = secondMessage - (Byte.MIN_VALUE - message);
            message = Byte.MIN_VALUE;
            return true;
        }
        overflowRest = 0;
        return false;
    }

    @Override
    public String getStringMessage() {
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
}

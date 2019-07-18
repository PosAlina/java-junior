package com.acme.edu.command;

public class IntCommand extends Command {
    private int message;
    private int overflowRest = 0;

    public IntCommand(int message){
        this.message = message;
        this.state = States.INT_STATE;
    }

    public int getMessage() {
        return message;
    }

    private boolean checkOverflow(int secondMessage) {
        if ((secondMessage > 0) && (message > Integer.MAX_VALUE - secondMessage)) {
            overflowRest = secondMessage - (Integer.MAX_VALUE - message);
            message = Integer.MAX_VALUE;
            return true;
        } else if ((secondMessage < 0) && (message < Integer.MIN_VALUE - secondMessage)) {
            overflowRest = secondMessage - (Integer.MIN_VALUE - message);
            message = Integer.MIN_VALUE;
            return true;
        }
        overflowRest = 0;
        return false;
    }

    @Override
    public void update() {
        isToBeSaved = false;
        isToFixOverflow = false;
        message = overflowRest;
        overflowRest = 0;
    }

    @Override
    public String getStringMessage() {
        return String.valueOf(message);
    }

    @Override
    public void accumulate(Command secondCommand) {
        if (secondCommand instanceof IntCommand) {
            isToFixOverflow = checkOverflow(((IntCommand) secondCommand).getMessage());
            isToBeSaved = isToFixOverflow;
            if (!isToFixOverflow) {
                message = message + ((IntCommand) secondCommand).getMessage();
            }
        } else {
            isToBeSaved = true;
        }
    }
}
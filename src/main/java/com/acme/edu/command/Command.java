package com.acme.edu.command;

import com.acme.edu.exceptions.SaveFailureException;
import com.acme.edu.saver.Saver;

public class Command {
    public enum States {
        NO_STATE(""),
        INT_STATE("primitive: "),
        BYTE_STATE("primitive: "),
        BOOLEAN_STATE("primitive: "),
        STRING_STATE("string: "),
        CHAR_STATE("char: "),
        REFERENCE_STATE("reference: "),
        INTARRAY_STATE("primitives array: "),
        INTMATRIX_STATE("primitives matrix: "),
        INTMULTIMATRIX_STATE("primitives multimatrix: ");

        private final String prefix;

        States(String prefix) {
            this.prefix = prefix;
        }

        public String getPrefix() {
            return prefix;
        }
    }

    protected States state;
    protected boolean isToBeSaved;
    protected boolean isToFixOverflow;
    protected String messageAsString;

    public Command() {
        this.isToBeSaved = false;
        this.isToFixOverflow = false;
        this.state = States.NO_STATE;
        this.messageAsString = null;
    }

    public boolean isToBeSaved() {
        return isToBeSaved;
    }

    public boolean isToFixOverflow() {
        return isToFixOverflow;
    }

    public String getMessageAsString() {
        return messageAsString;
    }

    public String decorate() {
        return state.getPrefix() + getMessageAsString();
    }

    public void accumulate(Command command) {
        isToBeSaved = true;
    }

    public void process(Command currentCommand, Saver saver) throws SaveFailureException {
        accumulate(currentCommand);
        saveCommand(saver);
    }

    public void saveCommand(Saver saver) throws SaveFailureException {
        saver.save(decorate());
    }
}
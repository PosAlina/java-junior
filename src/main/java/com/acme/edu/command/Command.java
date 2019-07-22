package com.acme.edu.command;

import com.acme.edu.saver.Saver;

public class Command {
    protected enum States {
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

        protected String getPrefix() {
            return prefix;
        }
    }

    protected States state;
    protected boolean isToBeSaved;
    protected boolean isToFixOverflow;

    public Command() {
        this.isToBeSaved = false;
        this.isToFixOverflow = false;
        this.state = States.NO_STATE;
    }

    public boolean isToBeSaved() {
        return isToBeSaved;
    }

    public boolean isToFixOverflow() {
        return isToFixOverflow;
    }

    public String getMessageAsString() {
        return "";
    }

    public String decorate() {
        return state.getPrefix() + getMessageAsString();
    }

    public void accumulate(Command command) {
        isToBeSaved = true;
    }

    public void update() {}

    public boolean isCompletelyProcessed() {
        return isToBeSaved;
    }

    public void process(Command currentCommand, Saver saver) {
        accumulate(currentCommand);
        if (isToBeSaved) {
            saver.save(decorate());
            if (isToFixOverflow) {
                update();
            }
        }
    }
}


package com.acme.edu.command;

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
        INTMATRIX_STATE("primitives matrix: ");

        private final String prefix;

        States(String prefix) {
            this.prefix = prefix;
        }

        public String getPrefix() {
            return prefix;
        }
    }

    protected States state;
    public boolean isToBeSaved;
    public boolean isToFixOverflow;

    public Command() {
        isToBeSaved = false;
        isToFixOverflow = false;
        this.state = States.NO_STATE;
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

    public void update() {
        isToBeSaved = false;
        isToFixOverflow = false;
    }

    public void process(Command currentCommand, Saver saver) {
        accumulate(currentCommand);
        if (isToBeSaved) {
            saver.save(decorate());
        }
    }
}


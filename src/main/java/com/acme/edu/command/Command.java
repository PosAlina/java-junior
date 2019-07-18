package com.acme.edu.command;

public class Command {
    public enum States {
        NO_STATE(""),
        INT_STATE("primitive: "),
        BYTE_STATE("primitive: "),
        BOOLEAN_STATE("primitive: "),
        STRING_STATE("string: "),
        CHAR_STATE("char: "),
        REFERENCE_STATE("reference: "),
        INTARRAY_STATE("primitives array: ");

        private final String prefix;

        States(String prefix) {
            this.prefix = prefix;
        }

        public String getPrefix() {
            return prefix;
        }

        public static boolean canBeAccumulated(States state) {
            return state == INT_STATE || state == BYTE_STATE || state == STRING_STATE;
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

    public String getStringMessage() {
        return "";
    }

    public String decorate() {
        if ("".equals(getStringMessage())) {
            return "null";
        }
        return state.getPrefix() + getStringMessage();
    }

    public void accumulate(Command command) {
        isToBeSaved = true;
    }

    public boolean isNoCommand() {
        return state == States.NO_STATE;
    }

    public void update() {
        isToBeSaved = false;
        isToFixOverflow = false;
    }
}


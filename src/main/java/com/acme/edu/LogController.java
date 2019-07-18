package com.acme.edu;

import com.acme.edu.command.Command;

public class LogController {
    private static Command accumulatedCommand = null;
    private Saver saver;

    public LogController(Saver saver) {
        this.saver = saver;
    }

    public void log(Command currentCommand) {
        if (accumulatedCommand == null) {
            accumulatedCommand = currentCommand;
            return;
        }
        accumulatedCommand.accumulate(currentCommand);
        if (accumulatedCommand.isToBeSaved) {
            saver.save(accumulatedCommand.decorate());
            if (accumulatedCommand.isToFixOverflow) {
                accumulatedCommand.update();
            }
            else {
                accumulatedCommand = currentCommand;
             }
        }
    }

    public void flush() {
        if (accumulatedCommand == null) return;
        saver.save(accumulatedCommand.decorate());
        accumulatedCommand = null;
    }

    public Saver getSaver() {
        return saver;
    }
/*    public void flush() {
        if (prevCommand.getState()!= Logger.States.NO_STATE) {
            saver.save(prevCommand.decorate());
            prevCommand=new Command("");
        }
    }*/
/*
//================================================ COMMAND
    //ALL TYPE
    public boolean isNeedsSaving() {
        return (!wasAccumulated); //Int: || (overflow != 0);
    }

    public void changed(Command command) {
        //Int: if (command.overflow != 0) {
            message = command.overflow;
        }
    }

    //ACCUMULATED TYPE
    private boolean equals(Command comand) {
        return (command instanceof THIS_TYPE)//String: && (...);
    }

    public void accumulate(Command command) {
        if (equals(command)) {
            //String: counter++;
            //Int: sum += ... overflow...
            wasAccumulated = true;
        }
    }

    //UNACCUMULATED TYPE
     public void accumulate(Command command) {}



*//*
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

        String getPrefix() {
            return prefix;
        }
    }
*/

/*    private Saver saver;
*//*    private static int sum = 0;
    private static int stringCounter = 0;
    private static String previousString = "";*//*
    private static Command prevCommand = new Command("");

    public LogController(Saver saver) {
        this.saver = saver;
    }

    private boolean isEqualStates(Command command) {
        return prevCommand.getState() == command.getState();
    }

    public void flush() {
        if (prevCommand.getState()!= Logger.States.NO_STATE) {
            saver.save(prevCommand.decorate());
            prevCommand=new Command("");
        }
    }*/
}

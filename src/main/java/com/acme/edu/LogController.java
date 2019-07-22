package com.acme.edu;

import com.acme.edu.command.Command;
import com.acme.edu.saver.Saver;

public class LogController {
    private Command accumulatedCommand;
    private Saver saver;
    private boolean changedState;

    public LogController(Saver saver) {
        this.saver = saver;
        this.accumulatedCommand = null;
        this.changedState = false;
    }

    public void log(Command currentCommand) {
        changedState = false;
        if (!hasCommand()) {
            storeCommand(currentCommand);
            return;
        }
        accumulatedCommand.process(currentCommand, saver);
        if (accumulatedCommand.isCompletelyProcessed()) {
            storeCommand(currentCommand);
        }
    }

    public boolean hasCommand() {
        return accumulatedCommand != null;
    }

    public boolean hasChangedState() {
        return changedState;
    }

    private void storeCommand(Command currentCommand) {
        accumulatedCommand = currentCommand;
        changedState = true;
    }


}
package com.acme.edu;

import com.acme.edu.command.Command;
import com.acme.edu.exceptions.FlushException;
import com.acme.edu.exceptions.LogException;
import com.acme.edu.exceptions.SaveException;
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

    public void log(Command currentCommand) throws LogException {
        changedState = false;
        if (!hasCommand()) {
            storeCommand(currentCommand);
            return;
        }
        accumulatedCommand.process(currentCommand, saver);
        if (accumulatedCommand.isToBeSaved()) {
            storeCommand(currentCommand);
        }
    }

    public boolean hasCommand() {
        return accumulatedCommand != null;
    }

    public boolean isChangedState() {
        return changedState;
    }

    private void storeCommand(Command currentCommand) {
        accumulatedCommand = currentCommand;
        changedState = true;
    }

    public void flush() throws LogException {
        try {
            if (!hasCommand()) {
                throw new FlushException("No command for output");
            }
        }
        catch(FlushException e) {
            throw new LogException(e.getMessage());
        }
    }
}
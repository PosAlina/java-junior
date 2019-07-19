package com.acme.edu;

import com.acme.edu.command.Command;
import com.acme.edu.saver.Saver;

public class LogController {
    private Command accumulatedCommand;
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
            update(currentCommand);
        }
    }

    public void flush() {
        if (accumulatedCommand == null) return;
        saver.save(accumulatedCommand.decorate());
        accumulatedCommand = null;
    }

    private void update(Command currentCommand) {
        if (accumulatedCommand.isToFixOverflow) {
            accumulatedCommand.update();
        } else {
            accumulatedCommand = currentCommand;
        }
    }
}

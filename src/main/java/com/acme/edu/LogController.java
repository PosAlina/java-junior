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
        accumulatedCommand.process(currentCommand, saver);
        if (accumulatedCommand.isToBeSaved && !accumulatedCommand.isToFixOverflow) {
            accumulatedCommand = currentCommand;
        }
    }
}
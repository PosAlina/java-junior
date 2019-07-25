package com.acme.edu.command;

import com.acme.edu.exceptions.LogException;
import com.acme.edu.exceptions.SaveException;
import com.acme.edu.saver.Saver;
import sun.rmi.runtime.Log;

public class Command {
    protected boolean isToBeSaved;
    protected boolean isToFixOverflow;
    protected String messageAsString;
    protected String prefix;

    public Command() {
        prefix = "";
        this.isToBeSaved = false;
        this.isToFixOverflow = false;
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

    public String getPrefix() { return prefix; }

    public String decorate() {
        return getPrefix() + getMessageAsString();
    }

    public void accumulate(Command command) {
        isToBeSaved = true;
    }

    public void process(Command currentCommand, Saver saver) throws LogException {
        accumulate(currentCommand);
        try {
            saveCommand(saver);
        } catch (SaveException e) {
            throw new LogException(e);
        }
    }

    public void saveCommand(Saver saver) throws SaveException {
        if (saver == null) {
            throw new SaveException("Unexisting saver");
        }
        saver.save(decorate());
    }
}
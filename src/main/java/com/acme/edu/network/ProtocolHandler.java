package com.acme.edu.network;

import com.acme.edu.command.*;
import com.acme.edu.exceptions.IncorrectMessageException;
import com.acme.edu.exceptions.LogException;

public class ProtocolHandler {
    public Command parseCommand(String message) throws LogException {
        if (!message.contains(" ") || !"log".equals(message.substring(0, message.indexOf(" ")))) {
            throw new IncorrectMessageException("Incorrect command sent to server");
        }
        message = message.substring(message.indexOf(" ") + 1);
        if (!message.contains(" ")) {
            throw new IncorrectMessageException("Incorrect command sent to server");
        }
        String messageType = message.substring(0, message.indexOf(" "));
        message = message.substring(message.indexOf(" ") + 1);
        if ("int".equals(messageType)) {
            return new IntCommand(message);
        } else if ("byte".equals(messageType)) {
            return new ByteCommand(message);
        } else if ("char".equals(messageType)) {
            return new CharCommand(message);
        } else if ("boolean".equals(messageType)) {
            return new BooleanCommand(message);
        } else if ("string".equals(messageType)) {
            return new StringCommand(message);
        }
        throw new IncorrectMessageException("Incorrect command sent to server");
    }
}

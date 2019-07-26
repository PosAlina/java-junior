package com.acme.edu.network.server;

import com.acme.edu.LogController;
import com.acme.edu.command.*;
import com.acme.edu.exceptions.IncorrectMessageException;
import com.acme.edu.exceptions.LogClientProxyException;
import com.acme.edu.exceptions.LogException;
import com.acme.edu.saver.ConsoleSaver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class LogServerProxy {
    public static void main(String[] args) throws LogException {
        LogController logController = new LogController(new ConsoleSaver());
        try (final ServerSocket serverSocket = new ServerSocket(666)) {
            while (true) {
                boolean closed = false;
                Socket client = serverSocket.accept();
                try (BufferedReader in =
                             new BufferedReader(
                                     new InputStreamReader(
                                             new BufferedInputStream(
                                                     client.getInputStream())));
                     BufferedWriter out =
                             new BufferedWriter(
                                     new OutputStreamWriter(
                                             new BufferedOutputStream(
                                                     client.getOutputStream())))) {
                    while (!closed) {
                        String message = in.readLine();
                        if ("flush".equals(message)) {
                            logController.flush();
                        } else if ("close".equals(message)) {
                            logController.close();
                            closed = true;
                        } else {
                            Command command = parseCommand(message);
                            logController.log(command);
                        }
                        out.write("Success!");
                        out.newLine();
                        out.flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                client.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Command parseCommand(String message) throws LogException {
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
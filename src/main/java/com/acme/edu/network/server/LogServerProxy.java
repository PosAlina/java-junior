package com.acme.edu.network.server;

import com.acme.edu.LogController;
import com.acme.edu.command.*;
import com.acme.edu.exceptions.ConnectionException;
import com.acme.edu.exceptions.LogException;
import com.acme.edu.network.Acceptor;
import com.acme.edu.network.ProtocolHandler;
import com.acme.edu.saver.ConsoleSaver;

import java.io.*;

public class LogServerProxy {
    private static Acceptor acceptor;
    private static LogController logController;
    private static ProtocolHandler protocolHandler;
    private static BufferedReader in;
    private static BufferedWriter out;
    private static final int PORT = 666;

/*    public static void main(String[] args) throws LogException {
        protocolHandler = new ProtocolHandler();
        try {
            acceptor = new Acceptor(PORT);
            while (true) {
                logController = new LogController(new ConsoleSaver());
                acceptor.accept();
                in = acceptor.getIn();
                out = acceptor.getOut();
                try {
                    while (acceptor.isConnectionOpen()) {
                        String message = receive();
                        if ("flush".equals(message)) {
                            logController.flush();
                        } else if ("close".equals(message)) {
                            logController.close();
                            send("Success!");
                            acceptor.close();
                            break;
                        } else {
                            Command command = protocolHandler.parseCommand(message);
                            logController.log(command);
                        }
                        send("Success!");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (ConnectionException e) {
            e.printStackTrace();
        }
    }*/

    private static void send(String message) throws IOException {
        out.write(message);
        out.newLine();
        out.flush();
    }

    private static String receive() throws IOException {
        return in.readLine();
    }
}
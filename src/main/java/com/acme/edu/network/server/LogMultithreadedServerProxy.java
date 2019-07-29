package com.acme.edu.network.server;

import com.acme.edu.LogController;
import com.acme.edu.command.Command;
import com.acme.edu.exceptions.ConnectionException;
import com.acme.edu.exceptions.LogException;
import com.acme.edu.exceptions.SaveException;
import com.acme.edu.network.Acceptor;
import com.acme.edu.network.ProtocolHandler;
import com.acme.edu.saver.FileSaver;

import java.io.*;
import java.net.ServerSocket;

public class LogMultithreadedServerProxy {
/*    public static void main(String[] args) throws IOException {
        int PORT = 666;
        ServerSocket serverSocket = new ServerSocket(PORT);
        new Thread(() -> {
            while (true) {
                try {
                    Acceptor acceptor = new Acceptor(serverSocket);
                    new Session(acceptor).start();
                } catch (ConnectionException | SaveException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }*/
}

class Session extends Thread {
    private BufferedReader in;
    private BufferedWriter out;
    private LogController logController = null;
    private ProtocolHandler protocolHandler;
    private Acceptor acceptor;

    public Session(Acceptor acceptor) throws ConnectionException, SaveException {
        protocolHandler = new ProtocolHandler();
        this.acceptor = acceptor;
        acceptor.accept();
        in = acceptor.getIn();
        out = acceptor.getOut();
    }

    @Override
    public void run() {
        try {
            logController = new LogController(new FileSaver(
                            "test_" + Thread.currentThread().getName() + ".txt"));
        } catch (SaveException e) {
            e.printStackTrace();
        }
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
        } catch (ConnectionException | LogException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                acceptor.close();
            } catch (ConnectionException e) {
            }
        }
    }

    private void send(String message) throws IOException {
        out.write(message);
        out.newLine();
        out.flush();
    }

    private String receive() throws IOException {
        return in.readLine();
    }
}
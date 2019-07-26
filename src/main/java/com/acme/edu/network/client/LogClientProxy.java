package com.acme.edu.network.client;

import com.acme.edu.command.*;
import com.acme.edu.exceptions.LogClientProxyException;
import com.acme.edu.exceptions.LogException;

import java.io.*;
import java.net.Socket;

public class LogClientProxy {
    private boolean connectionOpen = false;
    private Socket server;
    private BufferedReader in;
    private BufferedWriter out;

    public LogClientProxy() throws LogClientProxyException {
        openConnection();
        connectionOpen = true;
    }

    private void openConnection() throws LogClientProxyException {
        if (connectionOpen) return;
        try {
            this.server = new Socket("localhost", 666);
            in = new BufferedReader(
                            new InputStreamReader(
                                    new BufferedInputStream(
                                            server.getInputStream())));
            out = new BufferedWriter(
                            new OutputStreamWriter(
                                    new BufferedOutputStream(
                                            server.getOutputStream())));
            connectionOpen = true;
        } catch (IOException e) {
            e.printStackTrace();
            throw new LogClientProxyException("Unable to connect to server");
        }
    }

    public void closeConnection() throws LogClientProxyException {
        if (!connectionOpen) return;
        try {
            out.close();
            in.close();
            server.close();
            connectionOpen = false;
        } catch (IOException e) {
            e.printStackTrace();
            throw new LogClientProxyException("Unable to connect to server");
        }
    }

    private void send(String message) throws LogClientProxyException {
        try {
            out.write(message);
            out.newLine();
            out.flush();
            if (!("Success!".equals(in.readLine()))) {
                throw new LogClientProxyException("Couldn't log message: '" + message + "'. LogServerProxy not responding");
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new LogClientProxyException("Couldn't log message: '" + message + "'");
        }
    }

    public void log(byte message) throws LogException {
        openConnection();
        send("log byte " + String.valueOf(message));
    }

    public void log(int message) throws LogException {
        openConnection();
        send("log int " + String.valueOf(message));
    }

    public void log(boolean message) throws LogException {
        openConnection();
        send("log boolean " + String.valueOf(message));
    }

    public void log(char message) throws LogException {
        openConnection();
        send("log char " + String.valueOf(message));
    }

    private void log(Object message) throws LogException {
        openConnection();
        if (message instanceof Integer) {
        } else {
        }
    }

    private  void log(String message) throws LogException {
        openConnection();
        send("log string " + message);
    }

    public void log(int[] message) throws LogException {
        openConnection();
    }

    public void log(int[][] message) throws LogException {
        openConnection();
    }

    public void log(Object... message) throws LogException {
        if (message instanceof Object[][]) {
        } else {
            for (Object current : message) {
                log(current);
            }
        }
    }

    public void log(String... message) throws LogException {
        for (String current : message) {
            log(current);
        }
    }

    public void flush() throws LogException {
        openConnection();
        send("flush");
    }

    public void close() throws LogException {
        openConnection();
        send("close");
        closeConnection();
    }
}
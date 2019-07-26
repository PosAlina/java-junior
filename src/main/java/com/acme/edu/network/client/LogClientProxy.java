package com.acme.edu.network.client;

import com.acme.edu.command.*;
import com.acme.edu.exceptions.LogClientProxyException;
import com.acme.edu.exceptions.LogException;

import java.io.*;
import java.net.Socket;

public class LogClientProxy {/*
    private boolean connectionOpen = false;
    private Socket server;

    public LogClientProxy() throws LogClientProxyException {
        openConnection();
    }

    private void openConnection() throws LogClientProxyException {
        try {
            this.server = new Socket("localhost", 666);
        } catch (IOException e) {
            e.printStackTrace();
            throw new LogClientProxyException("Unable to connect to server");
        }
    }

    private void send(String message) throws LogClientProxyException {
        try (final BufferedReader in =
                     new BufferedReader(
                             new InputStreamReader(
                                     new BufferedInputStream(
                                             server.getInputStream())));
             final BufferedWriter out =
                     new BufferedWriter(
                             new OutputStreamWriter(
                                     new BufferedOutputStream(
                                             server.getOutputStream())))) {
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
        send("log byte " + String.valueOf(message));
    }

    public void log(int message) throws LogException {
        send("log int " + String.valueOf(message));
    }

    public void log(boolean message) throws LogException {
        send("log boolean " + String.valueOf(message));
    }

    public void log(char message) throws LogException {
        send("log char " + String.valueOf(message));
    }

    private void log(Object message) throws LogException {
        if (message instanceof Integer) {
        } else {
        }
    }

    private  void log(String message) throws LogException {
        send("log string " + message);
    }

    public void log(int[] message) throws LogException {
    }

    public void log(int[][] message) throws LogException {
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
        send("flush");
    }

    public void close() throws LogException {
        send("close");
    }*/
}
package com.acme.edu.network;

import com.acme.edu.exceptions.ConnectionException;
import com.acme.edu.exceptions.LogClientProxyException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Acceptor {
    private boolean connectionOpen;
    private ServerSocket serverSocket;
    private BufferedReader in;
    private BufferedWriter out;
    private Socket client;

    public Acceptor(int port) throws ConnectionException {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ConnectionException("Couldn't open server for listening");
        }
    }

    public Acceptor(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void accept() throws ConnectionException {
        if (connectionOpen) return;
        try {
            client = serverSocket.accept();
            in = new BufferedReader(
                    new InputStreamReader(
                            new BufferedInputStream(
                                    client.getInputStream())));
            out = new BufferedWriter(
                    new OutputStreamWriter(
                            new BufferedOutputStream(
                                    client.getOutputStream())));
            connectionOpen = true;
        } catch (IOException e) {
            e.printStackTrace();
            throw new ConnectionException("Unable to accept connection to client");
        }
    }

    public void close() throws ConnectionException {
        if (!connectionOpen) return;
        try {
            out.close();
            in.close();
            client.close();
            connectionOpen = false;
        } catch (IOException e) {
            e.printStackTrace();
            throw new ConnectionException("Error closing connection with client");
        }
    }

    public BufferedReader getIn() {
        return in;
    }

    public BufferedWriter getOut() {
        return out;
    }

    public boolean isConnectionOpen() {
        return connectionOpen;
    }
}

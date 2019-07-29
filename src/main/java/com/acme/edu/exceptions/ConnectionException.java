package com.acme.edu.exceptions;

public class ConnectionException extends Exception {
    public ConnectionException(String s) {
        super(s);
    }
    public ConnectionException() {
        super("Connection not established");
    }
}

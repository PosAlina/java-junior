package com.acme.edu.exceptions;

public class LogException extends Exception {
    public LogException(String message) { super("Failed to log:" +  message); }

    public LogException(Exception e) { super("Failed to log:", e); }
}

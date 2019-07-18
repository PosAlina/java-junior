package com.acme.edu;

import com.acme.edu.command.*;

public class Logger {
    private static LogController logController = new LogController(new ConsoleSaver());

    public static void log(byte message) {
        logController.log(new ByteCommand(message));
    }

    public static void log(int message) {
        logController.log(new IntCommand(message));
    }

    public static void log(boolean message) {
        logController.log(new BooleanCommand(message));
    }

    public static void log(char message) {
        logController.log(new CharCommand(message));
    }

    private static void log(Object message) {
        logController.log(new ObjectCommand(message));
    }

    private static void log(String message) {
        logController.log(new StringCommand(message));
    }

    public static void log(int[] message) {
        logController.log(new IntArrayCommand(message));
    }

    public static void log(Object... message) {
        for (Object current : message) {
            log(current);
        }
    }

    public static void log(String... message) {
        for (String current : message) {
            log(current);
        }
    }

    public static void flush() {
        logController.flush();
    }
}

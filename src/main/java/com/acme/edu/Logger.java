package com.acme.edu;

import com.acme.edu.command.*;
import com.acme.edu.exceptions.FlushFailureException;
import com.acme.edu.exceptions.SaveFailureException;
import com.acme.edu.saver.ConsoleSaver;

public class Logger {
    private static LogController logController = new LogController(new ConsoleSaver());

    public static void log(byte message) throws SaveFailureException {
        logController.log(new ByteCommand(message));
    }

    public static void log(int message) throws SaveFailureException {
        logController.log(new IntCommand(message));
    }

    public static void log(boolean message) throws SaveFailureException {
        logController.log(new BooleanCommand(message));
    }

    public static void log(char message) throws SaveFailureException {
        logController.log(new CharCommand(message));
    }

    private static void log(Object message) throws SaveFailureException {
        if (message instanceof Integer) {
            logController.log(new IntCommand((Integer)message));
        } else {
            logController.log(new ObjectCommand(message));
        }
    }

    private static void log(String message) throws SaveFailureException {
        logController.log(new StringCommand(message));
    }

    public static void log(int[] message) throws SaveFailureException {
        logController.log(new IntArrayCommand(message));
    }

    public static void log(int[][] message) throws SaveFailureException {
        logController.log(new IntMatrixCommand(message));
    }

    public static void log(Object... message) throws FlushFailureException, SaveFailureException {
        if (message instanceof Object[][]) {
            logController.log(new IntMultiMatrixCommand(message));
        } else {
            for (Object current : message) {
                log(current);
            }
        }
    }

    public static void log(String... message) throws SaveFailureException {
        for (String current : message) {
            log(current);
        }
    }

    public static void flush() throws FlushFailureException {
        logController.flush();
    }
}
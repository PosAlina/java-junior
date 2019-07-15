package com.acme.edu;

public class Logger {
    private static String getMessageType(Object message) {
        String typeName;
        if (message instanceof String) {
            typeName = "string";
        } else if (message instanceof Integer || message instanceof Byte || message instanceof Boolean) {
            typeName = "primitive";
        } else if (message instanceof Character) {
            typeName = "char";
        } else {
            typeName = "reference";
        }
        return typeName;
    }

    private static String decorate(Object message) {
        if (message == null) {
            return "null";
        }
        return getMessageType(message) + ": " + message;
    }

    private static void printToConsole(String message) {
        System.out.println(message);
    }

    public static void log(Object message) {
        printToConsole(decorate(message));
    }
}

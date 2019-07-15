package com.acme.edu;

public class Logger {

    private static void printToConsole(String message) {
        System.out.println(message);
    }

    public static void log(char message) {
        printToConsole("char: " + message);
    }

    public static void log(Object message) {
        String typeName;
        if (message instanceof String) {
            typeName = "string: ";
        } else if (message instanceof Integer || message instanceof Byte || message instanceof Boolean) {
            typeName = "primitive: ";
        } else if (message == null) {
            typeName = "null: ";
        } else {
            typeName = "reference: ";
        }
        printToConsole(typeName + message);
    }
}

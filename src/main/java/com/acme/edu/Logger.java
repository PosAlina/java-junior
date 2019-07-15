package com.acme.edu;

public class Logger {
    private static final String STRING_PREFIX = "string";
    private static final String PRIMITIVE_PREFIX = "primitive";
    private static final String CHAR_PREFIX = "char";
    private static final String REFERENCE_PREFIX = "reference";

    private static String decorate(String prefix, Object message) {
        if (message == null) {
            return "null";
        }
        return prefix + ": " + message;
    }

    private static void save(String message) {
        System.out.println(message);
    }

    public static void log(String message) {
        save(decorate(STRING_PREFIX, message));
    }

    public static void log(byte message) {
        save(decorate(PRIMITIVE_PREFIX, message));
    }

    public static void log(int message) {
        save(decorate(PRIMITIVE_PREFIX, message));
    }

    public static void log(boolean message) {
        save(decorate(PRIMITIVE_PREFIX, message));
    }

    public static void log(char message) {
        save(decorate(CHAR_PREFIX, message));
    }

    public static void log(Object message) {
        save(decorate(REFERENCE_PREFIX, message));
    }
}

package com.acme.edu;

public class Logger {
    private static final String STRING_PREFIX = "string: ";
    private static final String PRIMITIVE_PREFIX = "primitive: ";
    private static final String CHAR_PREFIX = "char: ";
    private static final String REFERENCE_PREFIX = "reference: ";

    private static boolean agregatingInt = false;
    private static int sum = 0;

    private static String decorate(String prefix, Object message) {
        if (message == null) {
            return "null";
        }
        return prefix + message;
    }

    private static void save(String message) {
        System.out.println(message);
    }

    public static void flush() {
        save(decorate(PRIMITIVE_PREFIX, sum));
        agregatingInt = false;
        sum = 0;
    }

    public static void log(String message) {
        if (agregatingInt) {
            flush();
        }
        save(decorate(STRING_PREFIX, message));
    }

    public static void log(byte message) {
        if (agregatingInt) {
            flush();
        }
        save(decorate(PRIMITIVE_PREFIX, message));
    }

    public static void log(int message) {
        if (message == Integer.MAX_VALUE) {
            flush();
            save(decorate(PRIMITIVE_PREFIX, message));
        }
        if (!agregatingInt) {
            agregatingInt = true;
        }
        sum = sum + message;
    }

    public static void log(boolean message) {
        if (agregatingInt) {
            flush();
        }
        save(decorate(PRIMITIVE_PREFIX, message));
    }

    public static void log(char message) {
        if (agregatingInt) {
            flush();
        }
        save(decorate(CHAR_PREFIX, message));
    }

    public static void log(Object message) {
        if (agregatingInt) {
            flush();
        }
        save(decorate(REFERENCE_PREFIX, message));
    }
}

package com.acme.edu;

public class Logger {
    private static final String STRING_PREFIX = "string: ";
    private static final String PRIMITIVE_PREFIX = "primitive: ";
    private static final String CHAR_PREFIX = "char: ";
    private static final String REFERENCE_PREFIX = "reference: ";

    private enum states {
        NO_STATE,
        INT_STATE,
        BYTE_STATE,
        STRING_STATE
    }
    private static states state = states.NO_STATE;
    private static int sum = 0;
    private static int stringCounter = 0;
    private static String previousString = "";

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
        if ((state == states.INT_STATE) || (state == states.BYTE_STATE)) {
            save(decorate(PRIMITIVE_PREFIX, sum));
            sum = 0;
        } else if (state == states.STRING_STATE) {
            if (stringCounter > 1) {
                previousString = previousString + " (x" + stringCounter + ")";
            }
            save(decorate(STRING_PREFIX, previousString));
            stringCounter = 0;
        }
    }

    public static void log(String message) {
        if (state != states.STRING_STATE || (!previousString.equals(message))) {
            flush();
            state = states.STRING_STATE;
            previousString = message;
        }
        stringCounter++;
    }

    public static void log(byte message) {
        if (state != states.BYTE_STATE) {
            flush();
            state = states.BYTE_STATE;
        } else {
            int intMessage = sum + message;
            if ((intMessage > Byte.MAX_VALUE) || ((intMessage < Byte.MIN_VALUE))) {
                flush();
            }
        }
        sum = sum + message;
    }

    public static void log(int message) {
        if (state != states.INT_STATE) {
            flush();
            state = states.INT_STATE;
        } else {
            long longMessage = sum;
            longMessage = longMessage + message;
            if (longMessage > Integer.MAX_VALUE) {
                message = message + sum - Integer.MAX_VALUE;
                sum = Integer.MAX_VALUE;
                flush();
            } else if (longMessage < Integer.MIN_VALUE) {
                message = message - Integer.MIN_VALUE + sum;
                sum = Integer.MIN_VALUE;
                flush();
            }
        }
        sum = sum + message;
    }

    public static void log(boolean message) {
        flush();
        save(decorate(PRIMITIVE_PREFIX, message));
    }

    public static void log(char message) {
        flush();
        save(decorate(CHAR_PREFIX, message));
    }

    public static void log(Object message) {
        flush();
        save(decorate(REFERENCE_PREFIX, message));
    }
}

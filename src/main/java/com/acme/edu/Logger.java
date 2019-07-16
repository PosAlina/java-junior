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

    private static boolean stateIsInt() {
        return state == states.INT_STATE;
    }

    private static boolean stateIsByte() {
        return state == states.BYTE_STATE;
    }

    private static boolean stateIsString() {
        return state == states.STRING_STATE;
    }

    public static void flush() {
        if (stateIsInt() || stateIsByte()) {
            save(decorate(PRIMITIVE_PREFIX, sum));
            sum = 0;
        } else if (stateIsString()) {
            if (stringCounter > 1) {
                previousString = previousString + " (x" + stringCounter + ")";
            }
            save(decorate(STRING_PREFIX, previousString));
            stringCounter = 0;
            previousString = "";
        }
        state = states.NO_STATE;
    }

    public static void log(String message) {
        if ((!stateIsString()) || (!previousString.equals(message))) {
            flush();
            state = states.STRING_STATE;
            previousString = message;
        }
        stringCounter++;
    }


    public static void log(byte message) {
        int remainder = message;
        if (!stateIsByte()) {
            flush();
        } else {
            if ((message > 0) && (sum > Byte.MAX_VALUE - message)) {
                remainder = remainder - (Byte.MAX_VALUE - sum);
                sum = Byte.MAX_VALUE;
                flush();
            } else if ((message < 0) && (sum < Byte.MIN_VALUE - message)) {
                remainder = remainder - (Byte.MIN_VALUE - sum);
                sum = Byte.MIN_VALUE;
                flush();
            }
        }
        state = states.BYTE_STATE;
        sum = sum + remainder;
    }

    public static void log(int message) {
        if (!stateIsInt()) {
            flush();
        } else {
            if ((message > 0) && (sum > Integer.MAX_VALUE - message)) {
                message = message - (Integer.MAX_VALUE - sum);
                sum = Integer.MAX_VALUE;
                flush();
            } else if ((message < 0) && (sum < Integer.MIN_VALUE - message)) {
                message = message - (Integer.MIN_VALUE - sum);
                sum = Integer.MIN_VALUE;
                flush();
            }
        }
        state = states.INT_STATE;
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

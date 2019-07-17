package com.acme.edu;

public class Logger {
    private static LogController logController = new LogController(new ConsoleSaver());

    private static States state = States.NO_STATE;
    private static int sum = 0;
    private static int stringCounter = 0;
    private static String previousString = "";

    private enum States {
        NO_STATE(""),
        INT_STATE("primitive: "),
        BYTE_STATE("primitive: "),
        BOOLEAN_STATE("primitive: "),
        STRING_STATE("string: "),
        CHAR_STATE("char: "),
        REFERENCE_STATE("reference: "),
        INTARRAY_STATE("primitives array: ");

        private final String prefix;

        States(String prefix) {
            this.prefix = prefix;
        }

        String getPrefix() {
            return prefix;
        }
    }

    private static String decorate(Object message) {
        if (message == null) {
            return "null";
        }
        return state.getPrefix() + message;
    }

    private static String decorate(int... message) {
        if (message.length == 0) {
            return state.getPrefix() + "{}";
        }
        StringBuilder decoratedMessage = new StringBuilder(state.getPrefix());
        decoratedMessage.append("{");
        int index = 0;
        while (index < message.length - 1) {
            decoratedMessage.append(message[index]).append(", ");
            index++;
        }
        decoratedMessage.append(message[index]).append("}");
        return decoratedMessage.toString();
    }

    private static boolean stateIsInt() {
        return state == States.INT_STATE;
    }

    private static boolean stateIsByte() {
        return state == States.BYTE_STATE;
    }

    private static boolean stateIsString() {
        return state == States.STRING_STATE;
    }

    public static void flush() {
        switch (state) {
            case INT_STATE:
            case BYTE_STATE: {
                logController.getSaver().save(decorate(sum));
                sum = 0;
                break;
            }
            case STRING_STATE: {
                if (stringCounter > 1) {
                    previousString = previousString + " (x" + stringCounter + ")";
                }
                logController.getSaver().save(decorate(previousString));
                stringCounter = 0;
                previousString = "";
                break;
            }
        }
        state = States.NO_STATE;
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
        state = States.BYTE_STATE;
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
        state = States.INT_STATE;
        sum = sum + message;
    }

    public static void log(boolean message) {
        flush();
        state = States.BOOLEAN_STATE;
        logController.getSaver().save(decorate(message));
    }

    public static void log(char message) {
        flush();
        state = States.CHAR_STATE;
        logController.getSaver().save(decorate(message));
    }

    private static void log(Object message) {
        flush();
        state = States.REFERENCE_STATE;
        logController.getSaver().save(decorate(message));
    }

    private static void log(String message) {
        if ((!stateIsString()) || (!previousString.equals(message))) {
            flush();
            state = States.STRING_STATE;
            previousString = message;
        }
        stringCounter++;
    }

    public static void log(int... message) {
        flush();
        state = States.INTARRAY_STATE;
        logController.getSaver().save(decorate(message));
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
}

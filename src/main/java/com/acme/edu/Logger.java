package com.acme.edu;

public class Logger {

    private static void print(String message) {
        System.out.println(message);
    }

    public static void log(char message) {
        print("char: " + message);
    }

    public static void log(Object message) {
        if (message instanceof String) {
            print("string: " + message);
        } else if (message instanceof Integer || message instanceof Byte || message instanceof Boolean) {
            print("primitive: " + message);
        } else {
            print("reference: " + message);
        }
    }
}

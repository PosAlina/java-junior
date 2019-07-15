package com.acme.edu;

public class Logger {

    private static void print(String message) {
        System.out.println(message);
    }

    public static void log(int message) {
        print("primitive: " + message);
    }

    public static void log(byte message) {
        print("primitive: " + message);
    }

    public static void log(boolean message) {
        print("primitive: " + message);
    }

    public static void log(char message) {
        print("char: " + message);
    }

    public static void log(Object message) {
        if (message instanceof String) {
            print("string: " + message);
        } else {
            print("reference: " + message);
        }
    }
}

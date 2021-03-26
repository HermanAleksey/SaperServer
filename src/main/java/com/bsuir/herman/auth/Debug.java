package com.bsuir.herman.auth;

public class Debug {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";

    public static final String ANSI_STRIKEOUT_PURPLE = "\u001B[35m";
    public static final String ANSI_STRIKEOUT_CYAN = "\u001B[36m";

    public static void printMapping(String mapping) {
        System.out.println("Was called mapping: " + ANSI_YELLOW + mapping + ANSI_RESET);
    }

    public static void printInfo(String msg) {
        System.out.println("Info: " + ANSI_BLUE + msg + ANSI_RESET);
    }

    public static void printWsRequest(int num, String msg) {
        System.out.println("Handling ws message #"+num+": " + ANSI_RED + msg + ANSI_RESET);
    }

    public static void printWsInfo(String msg) {
        System.out.println("WS info: " + ANSI_GREEN + msg + ANSI_RESET);
    }

    public static void printWsResponse(String msg) {
        System.out.println("WS response: " + ANSI_STRIKEOUT_PURPLE + msg + ANSI_RESET);
    }

}

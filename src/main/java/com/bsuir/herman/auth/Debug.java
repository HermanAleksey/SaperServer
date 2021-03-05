package com.bsuir.herman.auth;

public class Debug {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    public static void printMapping(String mapping){
        System.out.println("Was called mapping: " + ANSI_YELLOW +mapping+ANSI_RESET);
    }
}

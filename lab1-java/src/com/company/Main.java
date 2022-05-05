package com.company;

public class Main {
    public static void main(String[] args) {
        BreakThread breakThread = new BreakThread();
        new MainThread(1, 10, breakThread).start();
        new MainThread(2, 17, breakThread).start();
        new MainThread(3, 23, breakThread).start();
        new Thread(breakThread).start();
    }
}

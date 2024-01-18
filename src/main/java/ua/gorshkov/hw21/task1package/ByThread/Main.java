package ua.gorshkov.hw21.task1package.ByThread;

import ua.gorshkov.hw21.task1package.ByRunnable.MyRunnable;

public class Main {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
    }
}

package ua.gorshkov.hw21.task1package.ByRunnable;

public class Main {
    public static void main(String[] args) {
        Thread myThread = new Thread(new MyRunnable());
        myThread.start();
    }
}

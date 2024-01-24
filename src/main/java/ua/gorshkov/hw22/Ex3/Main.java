package ua.gorshkov.hw22.Ex3;

import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static AtomicInteger number = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> increment(), "Thread 1");
        Thread thread2 = new Thread(() -> increment(), "Thread 2");

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Number = " + number.get());
    }

    private static void increment() {
        for (int i = 0; i < 1000000; i++) {
            number.incrementAndGet();
        }
    }
}

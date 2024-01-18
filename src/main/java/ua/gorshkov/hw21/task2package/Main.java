package ua.gorshkov.hw21.task2package;

public class Main {
    public static void main(String[] args) {
        CounterThread counterThread = new CounterThread();
        counterThread.start();
        try {
            counterThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("MyClass.counter = " + MyClass.counter);
    }
}

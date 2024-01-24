package ua.gorshkov.hw22.Ex1;

public class Main {
    public static Integer number = 0;
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(Main::increment, "Thread 1");
        Thread thread2 = new Thread(Main::increment, "Thread 2");
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
        System.out.println("Number = " + number);
    }

    private static void increment() {
        for(int i = 0; i < 1000000; i++){
            number++;
        }
    }
}

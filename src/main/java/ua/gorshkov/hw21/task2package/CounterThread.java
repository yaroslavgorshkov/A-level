package ua.gorshkov.hw21.task2package;

public class CounterThread extends Thread{
    @Override
    public void run() {
       for(int i = 0; i < 5; i++){
           MyClass.counter++;
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

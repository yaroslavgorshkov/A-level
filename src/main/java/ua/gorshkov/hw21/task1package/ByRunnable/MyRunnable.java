package ua.gorshkov.hw21.task1package.ByRunnable;

import javax.sound.midi.Track;

public class MyRunnable implements Runnable{
    @Override
    public void run() {
        while(true){
            System.out.println("Hello, A-level!");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

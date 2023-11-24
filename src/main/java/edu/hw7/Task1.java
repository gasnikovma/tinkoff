package edu.hw7;

import java.util.concurrent.atomic.AtomicInteger;

public class Task1 {
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public Runnable getRunnable(int count){
        return ()->{
            for(int i=0;i<count;i++) {
                atomicInteger.incrementAndGet();
            }
        };
    }
    public int execute(int count){
        Thread one = new Thread(getRunnable(count));
        Thread two = new Thread(getRunnable(count));
        one.start();
        two.start();
        try {
            one.join();
            two.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return atomicInteger.intValue();
    }
}

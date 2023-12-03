package edu.hw8.task2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class FixedThreadPool implements ThreadPool {

    private final int threadsNumber;
    private final Thread[] threads;
    private final BlockingQueue<Runnable> taskQueue;

    public FixedThreadPool(final int threadsNumber) {
        this.threadsNumber = threadsNumber;
        this.threads = new Thread[threadsNumber];
        this.taskQueue = new ArrayBlockingQueue<>(threadsNumber * 2);
    }

    public static FixedThreadPool create(int threadLimit) {
        return new FixedThreadPool(threadLimit);
    }

    @Override
    public void start() {
        for (int i = 0; i < threadsNumber; i++) {
            threads[i] = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        Runnable task = taskQueue.take();
                        task.run();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
            threads[i].start();
        }
    }

    @Override
    public void execute(final Runnable runnable) {
        try {
            taskQueue.put(runnable);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void close() throws Exception {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }
}

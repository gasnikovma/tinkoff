package edu.hw8;

import edu.hw8.task2.Fibonacci;
import edu.hw8.task2.FixedThreadPool;
import edu.hw8.task2.ThreadPool;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2 {

    @Test
    void FibonacciTest() throws Exception {
        List<Integer> expected = List.of(1, 1, 2, 3, 5, 8, 13, 21, 34);
        ThreadPool threadPool = FixedThreadPool.create(6);
        List<Integer> fib = Collections.synchronizedList(new ArrayList<>(Collections.nCopies(expected.size(), 0)));
        CountDownLatch latch = new CountDownLatch(expected.size());
        threadPool.start();
        for (int i = 1; i <= expected.size(); ++i) {
            int index = i;
            threadPool.execute(() -> {
                try {
                    fib.set(index - 1, Fibonacci.getFibonacci(index));
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        threadPool.close();
        for (int i = 0; i < expected.size(); ++i) {
            assertEquals(expected.get(i), fib.get(i));
        }
    }
}

package edu.hw10.task2;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task2Test {
    @Test
    void calc() throws IOException {
        FibCalc target = new FibCalcImpl();
        FibCalc calc1 = CacheProxy.create(target, FibCalc.class);
        long startTime = System.currentTimeMillis();
        calc1.fib(10);
        long duration = System.currentTimeMillis() - startTime;

        startTime = System.currentTimeMillis();
        calc1.fib(10);
        long duration2 = System.currentTimeMillis() - startTime;
        assertTrue(duration > duration2);

    }
}

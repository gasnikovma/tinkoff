package edu.hw10.task2;

import org.junit.jupiter.api.Test;
import java.io.IOException;

public class Task2Test {
    @Test
    void calc() throws IOException {
        FibCalc target =  new FibCalcImpl();
        FibCalc calc1 = CacheProxy.create(target, FibCalc.class);
        long startTime = System.currentTimeMillis();
        calc1.fib(18);
        long duration = System.currentTimeMillis() - startTime;
        System.out.println(duration);
    }
}

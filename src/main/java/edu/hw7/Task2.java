package edu.hw7;

import java.util.stream.LongStream;

public class Task2 {
    private Task2() {

    }

    public static long getFactorial(long n) {
        return LongStream.rangeClosed(1, n).parallel().reduce((a, b) -> a * b).orElse(0);
    }
}

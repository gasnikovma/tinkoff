package edu.hw7.task4;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.LongStream;

public class MultiThreadMonteCarlo {
    private double pi;

    @SuppressWarnings("checkstyle:MagicNumber")
    public double countPI(long n) {
        AtomicLong circleCount = new AtomicLong(0);

        circleCount.addAndGet(LongStream.rangeClosed(1, n).parallel().filter(i -> {
            double pointX = ThreadLocalRandom.current().nextDouble(0, 1);
            double pointY = ThreadLocalRandom.current().nextDouble(0, 1);
            return Math.pow(pointX, 2) + Math.pow(pointY, 2) <= 1;
        }).count());
        pi = 4 * (1.0 * circleCount.get() / n);
        return pi;
    }

    public double getError() {
        return Math.abs(pi - Math.PI);
    }

    public double getPi() {
        return pi;
    }

}

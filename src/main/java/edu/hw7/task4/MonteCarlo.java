package edu.hw7.task4;

public class MonteCarlo {
    private double pi;

    @SuppressWarnings("checkstyle:MagicNumber")
    public double countPI(long n) {
        long i = 0;
        int circleCount = 0;
        double x = 0;
        double y = 0;
        while (i < n) {
            x = Math.random();
            y = Math.random();
            if (Math.pow(x, 2) + Math.pow(y, 2) < 1) {
                circleCount += 1;
            }
            i += 1;
        }
        pi = 4 * (1.0 * circleCount / n);
        return pi;
    }

    public double getError() {
        return Math.abs(pi - Math.PI);
    }

    public double getPi() {
        return pi;
    }

}

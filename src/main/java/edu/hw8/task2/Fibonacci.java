package edu.hw8.task2;

public class Fibonacci {
    private Fibonacci() {
    }

    public static int getFibonacci(final int elementNumber) {
        if (elementNumber == 1 || elementNumber == 0) {
            return elementNumber;
        }
        return getFibonacci(elementNumber - 1) + getFibonacci(elementNumber - 2);
    }
}

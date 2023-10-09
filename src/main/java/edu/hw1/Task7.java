package edu.hw1;

public final class Task7 {
    private Task7() {

    }

    private static String toBinary(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Negative number");
        }
        if (n == 0) {
            return " ";
        }
        return toBinary(n / 2) + (n % 2);
    }

    @SuppressWarnings("checkstyle:magicnumber")
    public static int rotateRight(int n, int shift) {
        if (n <= 0) {
            return -1;
        }
        int offset = shift;
        String s = toBinary(n).trim();
        int digit = Integer.parseInt(s);
        int cntDigits = Task2.countDigits(digit);
        while (offset > 0) {
            digit = (int) ((digit % 10) * Math.pow(10, cntDigits - 1) + (digit / 10));
            offset -= 1;
        }
        return Integer.parseInt(String.valueOf(digit), 2);
    }

    @SuppressWarnings("checkstyle:magicnumber")
    public static int rotateLeft(int n, int shift) {
        if (n <= 0) {
            return -1;
        }
        String s = toBinary(n).trim();
        int offset = shift;
        int digit = Integer.parseInt(s);
        int cntDigits = Task2.countDigits(digit);
        while (offset > 0) {
            digit = (int) ((digit % Math.pow(10, cntDigits - 1)) * 10 + (digit / Math.pow(10, cntDigits - 1)));
            offset -= 1;
        }
        return Integer.parseInt(String.valueOf(digit), 2);
    }
}


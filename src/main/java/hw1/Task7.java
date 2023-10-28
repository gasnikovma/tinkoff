package hw1;

public final class Task7 {

    private Task7() {

    }

    @SuppressWarnings("checkstyle:magicnumber")
    private static int decToBinary(int n) {
        int a = 1;
        for (int i = 31; i >= 0; i--) {
            int k = n >> i;
            if ((k & 1) > 0) {
                a *= 10;
                a += 1;
            } else {
                a *= 10;
            }
        }
        return a;
    }

    @SuppressWarnings("checkstyle:magicnumber")
    public static int rotateRight(int n, int shift) {
        if (n <= 0) {
            return -1;
        }
        int offset = shift;
        int digit = decToBinary(n);
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
        int digit = decToBinary(n);
        int offset = shift;
        int cntDigits = Task2.countDigits(digit);
        while (offset > 0) {
            digit = (int) ((digit % Math.pow(10, cntDigits - 1)) * 10 + (digit / Math.pow(10, cntDigits - 1)));
            offset -= 1;
        }
        return Integer.parseInt(String.valueOf(digit), 2);
    }
}


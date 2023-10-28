package hw1;

public final class Task2 {
    private Task2() {

    }

    private static final int CAPACITY = 10;

    public static int countDigits(int a) {
        int cnt = 0;
        if (a == 0) {
            return 1;
        }
        int digit = a;
        while (digit != 0) {
            cnt += 1;
            digit /= CAPACITY;
        }
        return cnt;
    }
}

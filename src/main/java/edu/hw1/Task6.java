package edu.hw1;

public final class Task6 {
    private Task6() {

    }

    static int step = 0;

    @SuppressWarnings("checkstyle:magicnumber")
    public static String getMax(int num) {
        int[] hash = new int[10];
        for (int i = 0; i < 10; i++) {
            hash[i] = 0;
        }
        StringBuilder ans = new StringBuilder();
        int cntDigits = Task2.countDigits(num);
        int input = num;
        while (input > 0) {
            hash[(input % 10)] += 1;
            input /= 10;
        }
        while (cntDigits != 4) {
            hash[0] += 1;
            cntDigits += 1;
        }

        for (int i = 9; i >= 0; i--) {
            for (int j = 0; j < hash[i]; j++) {
                ans.append(i);
            }
        }

        return String.valueOf(ans);
    }

    @SuppressWarnings("checkstyle:magicnumber")
    public static String getMin(int num) {
        int[] hash = new int[10];
        for (int i = 0; i < 10; i++) {
            hash[i] = 0;
        }
        int cntDigits = Task2.countDigits(num);
        StringBuilder ans = new StringBuilder();
        int input = num;
        while (input > 0) {
            hash[(input % 10)] += 1;
            input = input / 10;
        }
        while (cntDigits != 4) {
            hash[0] += 1;
            cntDigits += 1;
        }

        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j < hash[i]; j++) {
                ans.append(i);
            }
        }

        return String.valueOf(ans);
    }

    @SuppressWarnings("checkstyle:magicnumber")
    public static boolean same(int d) {
        int e = d;
        boolean flag = true;
        int n = e % 10;
        e /= 10;
        while (e != 0) {
            if (e % 10 != n) {
                flag = false;
                break;
            }
            e /= 10;

        }
        return flag;
    }

    @SuppressWarnings("checkstyle:magicnumber")
    public static int countK(int d) {
        if (d <= 0 || Task2.countDigits(d) != 4 || Task6.same(d)) {
            return -1;
        }
        return count(d);

    }

    @SuppressWarnings("checkstyle:magicnumber")
    private static int count(int d) {
        String max = getMax(d);
        int t;
        String min = getMin(d);
        int w = Integer.parseInt(max) - Integer.parseInt(min);
        if (w == 6174) {
            t = step;
            step = 0;
            return t;
        } else {
            step += 1;
            return count(w);
        }

    }
}

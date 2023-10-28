package hw1;

import java.util.Arrays;

@SuppressWarnings("checkstyle:magicnumber")
public final class Task6 {
    private static final int KAPREKAR = 6174;

    private Task6() {

    }

    private static String[] getMaxMin(int num) {
        int[] hash = new int[10];
        Arrays.fill(hash, 0);
        StringBuilder min = new StringBuilder();
        StringBuilder max = new StringBuilder();
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

        for (int i = hash.length - 1; i >= 0; i--) {
            for (int j = 0; j < hash[i]; j++) {
                max.append(i);
            }
        }
        for (int i = 0; i <= hash.length - 1; i++) {
            for (int j = 0; j < hash[i]; j++) {
                min.append(i);
            }
        }

        return new String[] {String.valueOf(max), String.valueOf(min)};
    }

    private static boolean isSame(int d) {
        int value = d;
        boolean flag = true;
        int n = value % 10;
        do {
            if (value % 10 != n) {
                flag = false;
                break;
            }
            value /= 10;
        } while (value != 0);
        return flag;
    }

    public static int countK(int d) {
        if (d <= 0 || Task2.countDigits(d) != 4 || Task6.isSame(d)) {
            return -1;
        }
        return count(d, 0);

    }

    private static int count(int d, int step) {
        String[] a = getMaxMin(d);
        int diff = Integer.parseInt(a[0]) - Integer.parseInt(a[1]);
        if (diff == KAPREKAR) {
            return step;
        } else {
            return count(diff, step + 1);
        }

    }
}

package edu.hw1;

@SuppressWarnings("checkstyle:magicnumber")
public final class Task6 {
    private Task6() {

    }

    static int[] hash = new int[10];
    static int step = 0;

    public static String getmax(int num) {
        for (int i = 0; i < 10; i++) {
            hash[i] = 0;
        }
        StringBuilder ans = new StringBuilder();
        int a = Task2.cnt(num);
        int e = num;
        while (e > 0) {
            hash[(e % 10)] += 1;
            e /= 10;
        }
        while (a != 4) {
            hash[0] += 1;
            a += 1;
        }

        for (int i = 9; i >= 0; i--) {
            for (int j = 0; j < hash[i]; j++) {
                ans.append(i);
            }
        }

        return String.valueOf(ans);
    }

    public static String getmin(int num) {
        for (int i = 0; i < 10; i++) {
            hash[i] = 0;
        }
        int a = Task2.cnt(num);
        StringBuilder ans = new StringBuilder();
        int e = num;
        while (e > 0) {
            hash[(e % 10)] += 1;
            e = e / 10;
        }
        while (a != 4) {
            hash[0] += 1;
            a += 1;
        }

        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j < hash[i]; j++) {
                ans.append(i);
            }
        }

        return String.valueOf(ans);
    }

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

    public static int countK(int d) {
        if (Task2.cnt(d) != 4 || Task6.same(d)) {
            return -1;
        }
        return count(d);

    }

    public static int count(int d) {
        String max = getmax(d);
        int t = 0;
        String min = getmin(d);
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

package edu.hw1;


@SuppressWarnings("checkstyle:magicnumber")
public final class Task3 {
    private Task3() {

    }

    public static boolean put(int[] a, int[] b) throws NullPointerException {
        if (a == null || b == null) {
            throw new NullPointerException("some message");
        }
        if (a.length == 0 || b.length == 0) {
            return false;
        }
        var min1 = a[0];
        var max1 = a[0];
        for (int num : a) {
            if (num < min1) {
                min1 = num;
            } else if (num > max1) {
                max1 = num;
            }
        }
        var min2 = b[0];
        var max2 = b[0];
        for (int num : b) {
            if (num < min2) {
                min2 = num;
            } else if (num > max2) {
                max2 = num;
            }
        }
        if (min1 > min2) {
            if (max2 > max1) {
                return true;
            }
            return false;
        }
        return false;

    }
}

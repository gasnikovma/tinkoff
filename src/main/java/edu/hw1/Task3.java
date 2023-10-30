package edu.hw1;

public final class Task3 {
    private Task3() {

    }

    public static boolean put(int[] a, int[] b) {
        if (a == null || b == null) {
            throw new IllegalArgumentException("at least one massive is null");
        }
        if (a.length == 0 || b.length == 0) {
            return false;
        }
        int[] values1 = getMinMax(a);
        int[] values2 = getMinMax(b);

        return values1[1] > values2[1] && values2[0] > values1[0];
    }

    private static int[] getMinMax(int[] a) {
        var min1 = a[0];
        var max1 = a[0];
        for (int num : a) {
            if (num < min1) {
                min1 = num;
            } else if (num > max1) {
                max1 = num;
            }
        }

        return new int[] {max1, min1};
    }
}

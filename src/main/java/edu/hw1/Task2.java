package edu.hw1;

@SuppressWarnings("checkstyle:magicnumber")
public final class Task2 {
    private Task2() {

    }

    public static int cnt(int a) {
        int c = 0;
        if (a == 0) {
            return 1;
        }
        int e = a;
        while (e != 0) {
            c += 1;
            e /= 10;
        }
        return c;
    }
}

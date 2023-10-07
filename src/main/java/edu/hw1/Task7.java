package edu.hw1;

@SuppressWarnings("checkstyle:magicnumber")
public final class Task7 {
    private Task7() {

    }

    public static String toBinary(int n) {
        if (n == 0) {
            return " ";
        }
        return toBinary(n / 2) + (n % 2);
    }

    public static int rotateLeft(int n, int shift) {
        if (n <= 0) {
            return -1;
        }
        String s = toBinary(n).trim();
        char[] a = s.toCharArray();
        char[] b = new char[a.length];
        System.arraycopy(a, shift, b, 0, a.length - shift);
        System.arraycopy(a, 0, b, a.length - shift, shift);
        return Integer.parseInt(new String(b), 2);
    }

    public static int rotateRight(int n, int shift) {
        if (n <= 0) {
            return -1;
        }
        String s = toBinary(n).trim();
        char[] a = s.toCharArray();
        char[] b = new char[a.length];
        System.arraycopy(a, 0, b, shift, a.length - shift);
        System.arraycopy(a, a.length - shift, b, 0, shift);
        return Integer.parseInt(new String(b), 2);
    }
}

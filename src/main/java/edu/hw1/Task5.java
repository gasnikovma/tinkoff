package edu.hw1;

@SuppressWarnings("checkstyle:magicnumber")
public final class Task5 {
    private Task5() {

    }

    public static boolean isPalindrom(int a) {
        int p = a;
        int reverse = 0;
        while (p != 0) {
            int r = p % 10;
            reverse = reverse * 10 + r;
            p = p / 10;
        }
        return a == reverse;
    }

    public static boolean isPalindromeDescendant(int s) {
        if (isPalindrom(s)) {
            return true;
        }
        if (Task2.cnt(s) % 2 == 1) {
            return false;
        } else {
            String str = String.valueOf(s);
            StringBuilder res;
            while (str.length() > 1) {
                res = new StringBuilder("");
                for (int i = 0; i < str.length() - 1; i += 2) {
                    int digit1 = Character.getNumericValue(str.charAt(i));
                    int digit2 = Character.getNumericValue(str.charAt(i + 1));
                    res.append(digit1 + digit2);
                }
                if (isPalindrom(Integer.parseInt(String.valueOf(res))) && res.length() != 1) {
                    return true;
                }
                if (res.length() == 1) {
                    return false;
                }
                str = String.valueOf(res);
            }
            return false;
        }
    }
}

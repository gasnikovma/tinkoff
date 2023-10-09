package edu.hw1;

public final class Task5 {
    private Task5() {

    }

    @SuppressWarnings("checkstyle:magicnumber")
    public static boolean isPalindrome(int a) {
        int input = a;
        int reverse = 0;
        while (input != 0) {
            int mod = input % 10;
            reverse = reverse * 10 + mod;
            input = input / 10;
        }
        return a == reverse;
    }

    @SuppressWarnings("checkstyle:magicnumber")
    public static boolean isPalindromeDescendant(int s) {
        if (isPalindrome(s)) {
            return true;
        }
        if (Task2.countDigits(s) % 2 == 1) {
            return false;
        }
        int input = s;
        int res;
        boolean flag;
        while (Task2.countDigits(input) > 1) {
            res = 0;
            int n = 1;
            flag = true;
            while (input != 0) {
                int digit1 = input % 10;
                input /= 10;
                int digit2 = input % 10;
                input /= 10;
                if (flag) {
                    res = (digit1 + digit2);
                    flag = false;
                } else {
                    int curRes = res;
                    n = (int) Math.pow(10, Task2.countDigits(curRes));
                    res = (digit1 + digit2) * n + res;
                }
            }
            boolean flag1 = true;
            boolean isPalindrome = isPalindrome(res);
            if (isPalindrome && Task2.countDigits(res) != 1) {
                flag1 = true;
            }
            if (Task2.countDigits(res) == 1) {
                flag1 = false;
            }
            if (isPalindrome || Task2.countDigits(res) == 1) {
                return flag1;
            }
            input = res;
        }
        return false;

    }

}

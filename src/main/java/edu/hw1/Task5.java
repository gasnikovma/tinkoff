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
        while (Task2.countDigits(input) > 1) {
            int res = 0;
            int n;
            boolean isFirstIteration = true;
            while (input != 0) {
                int digit1 = input % 10;
                input /= 10;
                int digit2 = input % 10;
                input /= 10;
                if (isFirstIteration) {
                    res = (digit1 + digit2);
                    isFirstIteration = false;
                } else {
                    int curRes = res;
                    n = (int) Math.pow(10, Task2.countDigits(curRes));
                    res = (digit1 + digit2) * n + res;
                }
            }
            boolean checkPalindrome = true;
            boolean isPalindrome = isPalindrome(res);
            int cntDigits = Task2.countDigits(res);
            if (isPalindrome && cntDigits != 1) {
                checkPalindrome = true;
            }
            if (cntDigits == 1) {
                checkPalindrome = false;
            }
            if (isPalindrome || cntDigits == 1) {
                return checkPalindrome;
            }
            input = res;
        }
        return false;

    }

}

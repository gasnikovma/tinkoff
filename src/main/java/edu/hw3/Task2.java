package edu.hw3;

import java.util.ArrayList;

public class Task2 {
    private Task2() {

    }

    private static final String MESSAGE = "ай-ай";

    public static ArrayList<String> clusterize(String s) {
        ArrayList<String> ans = new ArrayList<>();
        int balance = 0;
        int start = 0;
        int end = 0;
        int index = 0;
        do {
            if (s.charAt(index) == '(') {
                balance += 1;
                end += 1;
            } else if (s.charAt(index) == ')') {
                balance -= 1;
                end += 1;
            }
            if (balance < 0) {
                throw new IllegalArgumentException(MESSAGE);
            }
            if (balance == 0) {
                ans.add(s.substring(start, end));
                start = end;
            }
            index += 1;
        } while (index < s.length());
        if (balance > 0) {
            throw new IllegalArgumentException(MESSAGE);
        }

        return ans;
    }
}

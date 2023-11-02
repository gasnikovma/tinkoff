package edu.hw3;

import java.util.HashMap;

public class Task1 {
    private Task1() {

    }

    private static final int ALPHABET_SIZE = 26;
    private static final HashMap<Character, Character> CONVERTER = new HashMap<>();

    public static String atbash(String s) {
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            CONVERTER.put((char) ('A' + i), (char) ('A' + ALPHABET_SIZE - i - 1));
            CONVERTER.put((char) ('a' + i), (char) ('a' + ALPHABET_SIZE - i - 1));
        }
        StringBuilder a = new StringBuilder(s);
        for (int i = 0; i < s.length(); i++) {
            if (CONVERTER.containsKey(s.charAt(i))) {
                a.setCharAt(i, CONVERTER.get(s.charAt(i)));
            } else {
                a.setCharAt(i, s.charAt(i));
            }
        }
        return String.valueOf(a);
    }
}

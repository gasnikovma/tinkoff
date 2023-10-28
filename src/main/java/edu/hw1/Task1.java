package edu.hw1;

public final class Task1 {
    private static final int UPPER_SECONDS_LIMIT = 60;
    private static final String REGEX_DIGIT = "\\d+";

    private Task1() {

    }

    public static int getLength(String s) {
        if (s == null || s.isBlank()) {
            return -1;
        }
        String[] digits = s.replaceAll("[\\s\t]*", "").split(":");
        if (digits.length != 2 || !digits[0].matches(REGEX_DIGIT) || !digits[1].matches(REGEX_DIGIT)) {
            return -1;
        }

        int minutes = Integer.parseInt(digits[0]);
        int seconds = Integer.parseInt(digits[1]);
        if (seconds >= UPPER_SECONDS_LIMIT || minutes < 0 || seconds < 0) {
            return -1;
        }
        return minutes * UPPER_SECONDS_LIMIT + seconds;
    }
}

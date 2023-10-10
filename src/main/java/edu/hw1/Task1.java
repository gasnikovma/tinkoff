package edu.hw1;

public final class Task1 {
    private static final int SECONDS = 60;
    private static final int ZERO = 0;
    private static final String REGEX_DIGIT = "\\d+";

    private Task1() {

    }

    public static int getLength(String s) {
        if (s == null || s.isBlank()) {
            return -1;
        }
        String[] d = s.replaceAll("[\\s\t]*", "").split(":");
        if (d.length != 2 || !d[0].matches(REGEX_DIGIT) || !d[1].matches(REGEX_DIGIT)) {
            return -1;
        }

        int minutes = Integer.parseInt(d[0]);
        int seconds = Integer.parseInt(d[1]);
        if (seconds >= SECONDS || minutes < ZERO || seconds < ZERO) {
            return -1;
        }
        return minutes * SECONDS + seconds;
    }
}

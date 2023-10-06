package edu.hw1;

public final class Task1 {
    private Task1() {

    }

    @SuppressWarnings("checkstyle:magicnumber")
    public static int length(String s) {
        if (s == null || s.equals("")) {
            return -1;
        }
        String[] d = s.trim().split(":");
        if (d.length != 2) {
            return -1;
        }
        int minutes = Integer.parseInt(d[0].trim());
        int seconds = Integer.parseInt(d[1].trim());
        if (seconds >= 60 || minutes < 0 || seconds < 0) {
            return -1;
        }
        return minutes * 60 + seconds;
    }
}


package edu.hw5.task3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class DateLikeHandler extends Handler {
    public DaysAgoHandler nextHandler;

    public DateLikeHandler(DaysAgoHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @SuppressWarnings("checkstyle:magicnumber")
    public static String getFormat(String input) {
        String[] data;
        StringBuilder format = new StringBuilder();
        if (input.contains("/")) {
            data = input.split("/");
            if (data[0].length() > 2 || data[1].length() > 2 || data[2].length() > 4) {
                return null;
            }
            format.append("d".repeat(data[0].length()));
            format.append("/");
            format.append("M".repeat(data[1].length()));
            format.append("/");
            format.append("y".repeat(data[2].length()));
        } else if (input.contains("-")) {

            data = input.split("-");
            if (data[0].length() > 4 || data[1].length() > 2 || data[2].length() > 2) {
                return null;
            }
            format.append("y".repeat(data[0].length()));
            format.append("-");
            format.append("M".repeat(data[1].length()));
            format.append("-");
            format.append("d".repeat(data[2].length()));
        }
        return String.valueOf(format);

    }

    @Override
    public Optional<LocalDate> parseDate(String string) {
        if (string.contains("/") || string.contains("-")) {
            String pattern = getFormat(string);
            if (pattern != null) {
                return Optional.of(LocalDate.parse(string, DateTimeFormatter.ofPattern(pattern)));
            } else {
                return Optional.empty();
            }
        }

        return nextHandler.parseDate(string);
    }
}

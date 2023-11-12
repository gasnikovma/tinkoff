package edu.hw5;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class Task1 {
    private Task1() {

    }

    private static final int MINUTES_IN_HOUR = 60;

    public static String getAverageTime(List<String> input) {
        Duration durationSum = Duration.ofDays(0);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd, hh:mm");
        for (String i : input) {
            String[] date = i.split(" - ");
            String first = date[0];
            String second = date[1];
            Instant date1;
            Instant date2;
            try {
                date1 = simpleDateFormat.parse(first).toInstant();
                date2 = simpleDateFormat.parse(second).toInstant();
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            Duration between = Duration.between(date1, date2);
            durationSum = durationSum.plus(between);

        }
        durationSum = durationSum.dividedBy(input.size());
        long hours = durationSum.toHours();
        long minutes = durationSum.toMinutes() - hours * MINUTES_IN_HOUR;
        return "%dч %dм".formatted(hours, minutes);

    }
}

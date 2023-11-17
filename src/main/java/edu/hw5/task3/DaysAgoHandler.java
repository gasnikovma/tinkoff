package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

public class DaysAgoHandler extends Handler {

    @Override
    public Optional<LocalDate> parseDate(String string) {
        String[] data = string.split(" ");
        String regex = "\\d+";
        if (data[0] != null) {
            if (data[0].matches(regex) && (data[1].equals("day ago") || data[1].equals("days ago"))) {
                return Optional.of(LocalDate.now().minusDays(Integer.parseInt(data[0])));
            }
        }
        return Optional.empty();
    }
}

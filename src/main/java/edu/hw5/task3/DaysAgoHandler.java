package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

public class DaysAgoHandler extends Handler {

    @Override
    public Optional<LocalDate> parseDate(String string) {
        String[] data = string.split(" ");
        if (data[0] != null) {
            return Optional.of(LocalDate.now().minusDays(Integer.parseInt(data[0])));
        }
        return Optional.empty();
    }
}

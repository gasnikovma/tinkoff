package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

public class DateByWordHandler extends Handler {
    protected DateLikeHandler nextHandler;

    public DateByWordHandler(DateLikeHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public Optional<LocalDate> parseDate(String string) {
        return switch (string) {
            case "yesterday" -> Optional.of(LocalDate.now().minusDays(1));
            case "today" -> Optional.of(LocalDate.now());
            case "tomorrow" -> Optional.of(LocalDate.now().plusDays(1));
            default -> nextHandler.parseDate(string);
        };

    }
}

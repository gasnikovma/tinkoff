package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

public abstract class Handler {

    public abstract Optional<LocalDate> parseDate(String string);
}

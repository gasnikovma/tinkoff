package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class Task2 {
    private Task2() {

    }

    private static final int THIRTEEN = 13;

    public static List<LocalDate> getBadDays(int year) {
        List<LocalDate> answer = new ArrayList<>();
        LocalDate date = LocalDate.of(year, 1, THIRTEEN);
        while (date.getYear() == year) {
            if (date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                answer.add(date);
            }
            date = date.plusMonths(1);
        }
        return answer;
    }

    public static LocalDate getNextBadDay(LocalDate localDate) {
        LocalDate copy = localDate.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        while (copy.getDayOfMonth() != THIRTEEN) {
            copy = copy.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        }
        return copy;

    }
}

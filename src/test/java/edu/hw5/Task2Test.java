package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Task2Test {

    @ParameterizedTest
    @MethodSource("provider")
    @DisplayName("Пятницы 13-ого")
    void getBadDays(int year, List<LocalDate> expected) {
        List<LocalDate> get = Task2.getBadDays(year);
        assertEquals(get, expected);

    }

    private static Stream<Arguments> provider() {
        return Stream.of(Arguments.of(
            1925,
            List.of(LocalDate.parse("1925-02-13"), LocalDate.parse("1925-03-13"), LocalDate.parse("1925-11-13"))
        ), Arguments.of(2024, List.of(LocalDate.parse("2024-09-13"), LocalDate.parse("2024-12-13"))));
    }

    @Test
    void getNextBadDay() {
        LocalDate localDate = LocalDate.parse("1925-02-24");
        assertEquals(Task2.getNextBadDay(localDate), LocalDate.parse("1925-03-13"));

    }
}

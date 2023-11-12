package edu.hw5.task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class HandlerTest {

    @ParameterizedTest
    @MethodSource("provider")
    void parseDate(String date, Optional<LocalDate> expected) {
        Handler handler = new DateByWordHandler(new DateLikeHandler(new DaysAgoHandler()));
        assertEquals(handler.parseDate(date), expected);

    }

    private static Stream<Arguments> provider() {
        return Stream.of(
            Arguments.of("2020-10-10", Optional.of(LocalDate.parse("2020-10-10"))),
            Arguments.of("2020-12-2", Optional.of(LocalDate.parse("2020-12-02"))),
            Arguments.of("1/3/1976", Optional.of(LocalDate.parse("1976-03-01"))),
            Arguments.of("tomorrow", Optional.of(LocalDate.parse("2023-11-13"))),
            Arguments.of("yesterday", Optional.of(LocalDate.parse("2023-11-11"))),
            Arguments.of("1 day ago", Optional.of(LocalDate.parse("2023-11-11")))
        );
    }
}

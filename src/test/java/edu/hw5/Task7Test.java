package edu.hw5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class Task7Test {
    private static Stream<Arguments> providerThirdZero() {
        return Stream.of(
            Arguments.of("10001", true),
            Arguments.of("00011", true),
            Arguments.of("00111", false),
            Arguments.of("210", false)
        );
    }

    @MethodSource("providerThirdZero")
    @ParameterizedTest
    void thirdZero(String string, boolean expected) {
        boolean result = Task7.thirdZero(string);

        assertEquals(expected, result);

    }

    private static Stream<Arguments> providerStartAndEndSame() {
        return Stream.of(
            Arguments.of("000", true),
            Arguments.of("00011", false),
            Arguments.of("010", true),
            Arguments.of("101", true)
        );
    }

    @ParameterizedTest
    @MethodSource("providerStartAndEndSame")
    void startAndEndTheSame(String string, boolean expected) {
        boolean result = Task7.startAndEndTheSame(string);

        assertEquals(expected, result);
    }

    private static Stream<Arguments> providerLengthFromOneToThree() {
        return Stream.of(
            Arguments.of("010", true),
            Arguments.of("01011", false),
            Arguments.of("0011", false),
            Arguments.of("010", true),
            Arguments.of("101", true)
        );
    }

    @ParameterizedTest
    @MethodSource("providerLengthFromOneToThree")
    void lengthFromOneToThree(String string, boolean expected) {
        boolean result = Task7.lengthFromOneToThree(string);

        assertEquals(expected, result);
    }
}

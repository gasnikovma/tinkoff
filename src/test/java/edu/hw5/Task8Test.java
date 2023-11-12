package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class Task8Test {
    private static Stream<Arguments> providerUnevenLength() {
        return Stream.of(
            Arguments.of("000", true),
            Arguments.of("00011", true),
            Arguments.of("00111", true),
            Arguments.of("0101", false),
            Arguments.of("2", false)
        );
    }

    @MethodSource("providerUnevenLength")
    @ParameterizedTest
    void unevenLength(String string, boolean expected) {
        boolean result = Task8.unevenLength(string);

        assertEquals(expected, result);
    }

    private static Stream<Arguments> providerStartsWithZeroAndUnevenLengthOrStartsWithOneAndEvenLength() {
        return Stream.of(
            Arguments.of("000", true),
            Arguments.of("00111", true),
            Arguments.of("010", true),
            Arguments.of("101", false),
            Arguments.of("10", true)
        );
    }

    @MethodSource("providerStartsWithZeroAndUnevenLengthOrStartsWithOneAndEvenLength")
    @ParameterizedTest
    void startsWithZeroAndUnevenLengthOrStartsWithOneAndEvenLength(String string, boolean expected) {
        boolean result = Task8.startsWithZeroAndUnevenLengthOrStartsWithOneAndEvenLength(string);
        assertEquals(expected, result);
    }

    private static Stream<Arguments> providerEveryOddLetterIsOne() {
        return Stream.of(
            Arguments.of("000", false),
            Arguments.of("00011", false),
            Arguments.of("10111", true),
            Arguments.of("010", false),
            Arguments.of("101", true)
        );
    }

    @MethodSource("providerEveryOddLetterIsOne")
    @ParameterizedTest
    void everyOddLetterIsOne(String string, boolean expected) {
        boolean result = Task8.everyOddLetterIsOne(string);
        assertEquals(expected, result);
    }

    private static Stream<Arguments> providerMoreThanOneZeroAndLessThanTwoOne() {
        return Stream.of(
            Arguments.of("000", true),
            Arguments.of("00011", false),
            Arguments.of("00111", false),
            Arguments.of("111", false),
            Arguments.of("1111", false),
            Arguments.of("01111", false),
            Arguments.of("000100100", false)
        );
    }

    @MethodSource("providerMoreThanOneZeroAndLessThanTwoOne")
    @ParameterizedTest
    void moreThanOneZeroAndLessThanTwoOne(String string, boolean expected) {
        boolean result = Task8.moreThanOneZeroAndLessThanTwoOne(string);
        assertEquals(expected, result);
    }

    private static Stream<Arguments> providerNoSequentialOne() {
        return Stream.of(
            Arguments.of("000", true),
            Arguments.of("00011", false),
            Arguments.of("010", true),
            Arguments.of("101", true),
            Arguments.of("10", true),
            Arguments.of("1000", true),
            Arguments.of("100110000", false)
        );
    }

    @ParameterizedTest
    @MethodSource("providerNoSequentialOne")
    void noSequentialOne(String string, boolean expected) {
        boolean result = Task8.noSequentialOne(string);
        assertEquals(expected, result);
    }
}

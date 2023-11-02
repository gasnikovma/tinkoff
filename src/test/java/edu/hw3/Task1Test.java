package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class Task1Test {

    @ParameterizedTest
    @MethodSource("providerHello")
    @DisplayName("Hello world!")
    void atbash_shouldReturnEquals(String input, String output) {
        String atbashGiven = Task1.atbash(input);
        assertEquals(atbashGiven, output);

    }

    @ParameterizedTest
    @MethodSource("providerRussian")
    @DisplayName("Hello!")
    void atbash_shouldReturnEqualsWithRussian(String input, String output) {
        String atbashGiven = Task1.atbash(input);
        assertEquals(atbashGiven, output);

    }

    private static Stream<Arguments> providerHello() {
        return Stream.of(
            Arguments.of("Hello world!", "Svool dliow!")
        );

    }

    private static Stream<Arguments> providerRussian() {
        return Stream.of(
            Arguments.of("Hello, Петя!", "Svool, Петя!")
        );

    }

}

package edu.hw3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class Task2Test {

    @ParameterizedTest
    @MethodSource("providerCorrect")
    void clusterize_shouldReturnEquals(String s, List<String> ans) {
        assertEquals(Task2.clusterize(s), ans);
    }

    @ParameterizedTest
    @MethodSource("providerIncorrect")
    void clusterize_shouldThrowException(String s) {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Task2.clusterize(s);
            ;
        });
        assertEquals(thrown.getMessage(), "ай-ай");
    }

    private static Stream<Arguments> providerCorrect() {
        return Stream.of(
            Arguments.of("()()()", Arrays.asList("()", "()", "()")),
            Arguments.of("((()))(())()()(()())", Arrays.asList("((()))", "(())", "()", "()", "(()())"))
        );

    }

    private static Stream<Arguments> providerIncorrect() {
        return Stream.of(
            Arguments.of("())()"),
            Arguments.of("((()))())()()(()())")
        );

    }
}

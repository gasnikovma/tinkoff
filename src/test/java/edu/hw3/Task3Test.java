package edu.hw3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class Task3Test {

    @ParameterizedTest
    @MethodSource("provider") <T> void getFrequency(List<T> input, Map<T, Integer> expected) {
        assertEquals(expected, Task3.getFrequency(input));
    }

    private static Stream<Arguments> provider() {
        return Stream.of(
            Arguments.of(List.of("a", "bb", "a", "bb"), Map.of("bb", 2, "a", 2)),
            Arguments.of(List.of("код", "код", "код", "bug"), Map.of("код", 3, "bug", 1))
        );

    }
}

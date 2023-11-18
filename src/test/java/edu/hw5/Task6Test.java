package edu.hw5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class Task6Test {
    private static Stream<Arguments> provideNumber() {
        return Stream.of(
            Arguments.of(
                "qwerty",
                "dgfewwqwertydwfcxc", true
            )
        );
    }

    @ParameterizedTest
    @MethodSource("provideNumber")
    void isSub(String pattern, String string, boolean expected) {
        boolean result = Task6.isSubstring(pattern, string);

        assertEquals(expected, result);
    }
}

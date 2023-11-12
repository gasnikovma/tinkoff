package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class Task4Test {
    private static Stream<Arguments> provider() {
        return Stream.of(
            Arguments.of(
                "qwerty",
                false
            ),
            Arguments.of(
                "%@",
                true
            )
        );
    }

    @MethodSource("provider")
    @ParameterizedTest
    void Task4_validatePassword(String pass, boolean expected) {
        boolean result = Task4.isSuitablePassword(pass);

        assertEquals(expected, result);
    }
}

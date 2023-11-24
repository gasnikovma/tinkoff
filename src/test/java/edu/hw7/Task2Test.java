package edu.hw7;

import edu.hw2.Task1;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class Task2Test {

    @ParameterizedTest
    @MethodSource("provider")
    void getFactorial(long input, long expected) {
        assertEquals(Task2.getFactorial(input),expected);
    }

    private static Stream<Arguments> provider() {
        return Stream.of(Arguments.of(13, 6227020800L), Arguments.of(6, 720), Arguments.of(10L, 3628800));
    }
}

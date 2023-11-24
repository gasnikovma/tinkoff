package edu.hw7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {

    @ParameterizedTest @MethodSource("provider") @DisplayName("атомарные операции -  инкремент")
    void atomicOperationsTest(int cnt, int expected) {
        Task1 task1 = new Task1();
        assertEquals(task1.execute(cnt), expected);
    }

    private static Stream<Arguments> provider() {
        return Stream.of(Arguments.of(30, 60), Arguments.of(2545, 5090), Arguments.of(32000, 64000));
    }
}

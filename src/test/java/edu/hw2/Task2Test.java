package edu.hw2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {
    @ParameterizedTest
    @MethodSource("providerForSquare")
    void area_ShouldReturnEquals(Task2.Shape shape) {
        assertEquals(shape.area(), 400);

    }

    @Test
    @DisplayName("площадь квадрата")
    void area_Square() {
        Task2.Square square = new Task2.Square(31);
        assertEquals(square.area(), 961);
    }

    @Test
    @DisplayName("площадь прямоугольника")
    void area_Rectangle() {
        Task2.Rectangle square = new Task2.Rectangle(10, 41);
        assertEquals(square.area(), 410);
    }

    private static Stream<Arguments> providerForSquare() {
        return Stream.of(
            Arguments.of(new Task2.Rectangle(40, 10)),
            Arguments.of(new Task2.Square(20))
        );

    }

}

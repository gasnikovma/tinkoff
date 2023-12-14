package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class ReverseIteratorTest {

    @ParameterizedTest
    @MethodSource("provider")
    @DisplayName("вывод элементов в обратном порядке")
    void ReverseIterator_shouldIterateInReverseOrder(List<Integer> a, List<Integer> b) {
        ReverseIterator<Integer> iterator = new ReverseIterator<>(a);
        int index = 0;
        while (iterator.hasNext()) {
            assertEquals(iterator.next(), b.get(index));
            index += 1;
        }

    }

    private static Stream<Arguments> provider() {
        return Stream.of(
            Arguments.of(List.of(1, 2, 3), List.of(3, 2, 1))
        );
    }

}

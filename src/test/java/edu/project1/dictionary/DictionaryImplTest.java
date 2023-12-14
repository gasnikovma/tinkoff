package edu.project1.dictionary;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DictionaryImplTest {
    Configuration a = new Configuration();

    @ParameterizedTest
    @MethodSource("provide")
    void DictionaryImpl_ShouldReturnSet(String word, Set<Character> unique) {
        Set<Character> characters = a.getUniqueChars(word);
        assertEquals(unique, characters);
    }

    @ParameterizedTest
    @MethodSource("provideForPositions")
    void DictionaryImpl_ShouldReturnList(String word, char letter, List<Integer> indexes) {
        List<Integer> positions = a.getLetterPositions(word, letter);
        assertEquals(positions, indexes);
    }

    private static Stream<Arguments> provide() {
        return Stream.of(Arguments.of(
            "programming",
            new HashSet<>(Arrays.asList('p', 'r', 'o', 'g', 'a', 'm', 'i', 'n'))
        ));
    }

    private static Stream<Arguments> provideForPositions() {
        return Stream.of(Arguments.of("programming", 'g', Arrays.asList(3, 10)));
    }

}

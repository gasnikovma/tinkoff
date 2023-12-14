package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import static org.junit.jupiter.api.Assertions.*;

class NullComparatorTest {

    @Test
    @DisplayName("тест на проверку содержания null ключа в TreeMap")
    void NullComparator_shouldReturnTrue() {
        Map<Integer, Double> treeMap = new TreeMap<>(new NullComparator<>(Comparator.naturalOrder()));
        treeMap.put(null, 12d);
        assertTrue(treeMap.containsKey(null));
    }
}

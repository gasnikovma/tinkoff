package edu.hw1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Task5Test {
    @ParameterizedTest
    @ValueSource(ints = {11211230, 13001120, 3, 23336014, 222, 11})
    void isPalindromeDescendant_ShouldReturnTrue(int arg) {
        assertTrue(Task5.isPalindromeDescendant(arg));
    }

    @ParameterizedTest
    @ValueSource(ints = {112, 13005120, 23536014})
    void isPalindromeDescendant_ShouldReturnFalse(int arg) {
        assertFalse(Task5.isPalindromeDescendant(arg));
    }

}

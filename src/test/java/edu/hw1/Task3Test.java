package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Task3Test {
    @Test
    @DisplayName("Проверка на null")
    void put_shouldThrow_Exception() {
        NullPointerException thrown = Assertions.assertThrows(NullPointerException.class, () -> {
            int[] a = null;
            int[] b = {1, 4, 5, 6, 9};
            Task3.put(a, b);
        });
        Assertions.assertEquals("some message", thrown.getMessage());
    }

    @Test
    @DisplayName("Проверка на пустоту")
    void put_shouldReturnFalse() {
        int[] a = {};
        int[] b = {1, 4, 5, 6, 9};
        boolean d = Task3.put(a, b);
        assertFalse(d);
    }

    @Test
    @DisplayName("Проверка на вложенность")
    void put_shouldReturnTrue() {
        int[] a = {3, 1};
        int[] b = {0, 4};
        assertTrue(Task3.put(a, b));
    }

    @Test
    @DisplayName("Проверка на вложенность(false)")
    void put_shouldReturnFalse_whenMAX1EqualsMAX2() {
        int[] a = {9, 9, 8};
        int[] b = {8, 9};
        assertFalse(Task3.put(a, b));
    }

    @Test
    @DisplayName("Обратная вложенность")
    void put_shouldReturnFalse_whenMIN1LessMIN2() {
        int[] a = {1, 2, 3, 4};
        int[] b = {2, 3};
        assertFalse(Task3.put(a, b));
    }

    @Test
    @DisplayName("Максимальное значение")
    void put_shouldReturnFalse_whenIntegerMaxValue() {
        int[] a = {1, 3, Integer.MAX_VALUE};
        int[] b = {0, 2, 3, 5, 6, 100000};
        assertFalse(Task3.put(a, b));
    }

}

package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task8Test {
    @Test
    @DisplayName("Проверка на null")
    void knightBoardCapture_shouldThrowException_WhenNull() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            int[][] a = null;
            Task8.knightBoardCapture(a);
        });
        Assertions.assertEquals("Invalid array size", thrown.getMessage());
    }

    @Test
    @DisplayName("Проверка на некорректные размеры массивы")
    void knightBoardCapture_shouldThrowException_WhenInvalidtSize() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            int[][] a = {
                {0, 0, 0, 0, 1, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0}
            };
            Task8.knightBoardCapture(a);
        });
        Assertions.assertEquals("Invalid array size", thrown.getMessage());
    }

    @Test
    @DisplayName("Проверка на некорректные размеры массивы")
    void knightBoardCapture_shouldThrowException_WhenInvalidData() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            int[][] a = {
                {0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, -1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0}
            };
            Task8.knightBoardCapture(a);
        });
        Assertions.assertEquals("Incorrect data", thrown.getMessage());
    }

    @Test
    @DisplayName("Не бьют друг друга")
    void knightBoardCapture_shouldReturnTrue() {
        int[][] a = {
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 0}
        };
        boolean res = Task8.knightBoardCapture(a);
        assertTrue(res);
    }

    @Test
    @DisplayName("Бьют друг друга")
    void knightBoardCapture_shouldReturnFalse() {
        int[][] a = {
            {1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 1, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 1, 0, 1, 0, 1}
        };
        boolean res = Task8.knightBoardCapture(a);
        assertFalse(res);
    }
}

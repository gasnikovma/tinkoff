package edu.hw3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class Task4Test {

    @ParameterizedTest
    @CsvSource(
        {
            "2, II",
            "12, XII",
            "16,  XVI"
        }
    )
    void convertToRoman_shouldReturnEquals(int arab, String roman) {
        assertEquals(Task4.convertToRoman(arab), roman);
    }
}

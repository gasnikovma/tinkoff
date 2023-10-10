package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task1Test {
    @Test
    @DisplayName("Пустая строка")
    void length_shouldReturnError_whenInputIsEmpty() {
        //given
        String s = "";
        //when
        int res = Task1.getLength(s);
        //then
        assertThat(res).isEqualTo(-1);
    }

    @Test
    @DisplayName("Корректная строка с многочисленными пробелами")
    void length_shouldReturnAnswer() {
        //given
        String s = "  999   :59  ";
        //when
        int res = Task1.getLength(s);
        //then
        assertThat(res).isEqualTo(59999);
    }

    @Test
    @DisplayName("Строка, содержащая число секунд больше 60")
    void length_shouldReturnError_when_TheNumber_Of_Seconds_Is_More_Than_Sixty() {
        //given
        String s = "999:63";
        //when
        int res = Task1.getLength(s);
        //then
        assertThat(res).isEqualTo(-1);
    }

    @ParameterizedTest
    @ValueSource(strings = {"34", "12:56:32"})
    @DisplayName("Некорректный ввод")
    void length_shouldReturnError_when_InvalidInput(String a) {
        int res = Task1.getLength(a);
        //then
        assertThat(res).isEqualTo(-1);
    }
    @Test
    @DisplayName("Строка, содержащая буквы вместо цифр")
    void length_shouldReturnError_whenInputNotContainDigits() {
        //given
        String s = "12:be";
        //when
        int res = Task1.getLength(s);
        //then
        assertThat(res).isEqualTo(-1);
    }

}

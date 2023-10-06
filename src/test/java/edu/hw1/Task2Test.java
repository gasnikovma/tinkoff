package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task2Test {
    @Test
    @DisplayName("Ноль")
    void cnt_shouldReturnOne() {
        //given
        int a = 0;
        //when
        int res = Task2.cnt(a);
        //then
        assertThat(res).isEqualTo(1);
    }
    @Test
    @DisplayName("Отрицательное число")
    void cnt_shouldReturnThree(){
        //given
        int a = -134;
        //when
        int res = Task2.cnt(a);
        //then
        assertThat(res).isEqualTo(3);
    }
    @Test
    @DisplayName("Отрицательное число")
    void cnt_shouldReturnSix(){
        //given
        int a = 832423;
        //when
        int res = Task2.cnt(a);
        //then
        assertThat(res).isEqualTo(6);
    }

}

package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task6Test {
    @ParameterizedTest
    @CsvSource({
        "3524,  3",
        "6621, 5",
        "1234, 3",
        "6554, 4"
    })
    void countK_ShouldReturnValue(int input, int res) {
        assertThat(Task6.countK(input) + 1).isEqualTo(res);
    }

    @Test
    @DisplayName("Нечетырехзначное число")
    void countK_ShouldReturnMinus_1() {
        int a = 999;
        int res = Task6.countK(a);
        assertThat(res).isEqualTo(-1);
    }

    @Test
    @DisplayName("Все цифры одинаковые")
    void countK_ShouldReturnMinus_1_whenTheDigitsAreTheSame() {
        int a = 4444;
        int res = Task6.countK(a);
        assertThat(res).isEqualTo(-1);
    }

}

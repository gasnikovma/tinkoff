package hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task7Test {

    @Test
    void rotateRight_ShouldReturnFour() {
        int n = 8;
        int shift = 1;
        int res = Task7.rotateRight(n, shift);
        assertThat(res).isEqualTo(4);
    }

    @Test
    void rotateLeft_ShouldReturnOne() {
        int n = 16;
        int shift = 1;
        int res = Task7.rotateLeft(n, shift);
        assertThat(res).isEqualTo(1);
    }

    @Test
    void rotateLeft_ShouldReturnSix() {
        int n = 17;
        int shift = 2;
        int res = Task7.rotateLeft(n, shift);
        assertThat(res).isEqualTo(6);
    }

    @Test
    void RotateLeft_ShouldReturnMinusOne_whenTheValueIsNegative() {
        int n = -3;
        int shift = 6;
        int res = Task7.rotateLeft(n, shift);
        assertThat(res).isEqualTo(-1);
    }
}

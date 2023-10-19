package edu.hw2;

import edu.hw2.task2.Rectangle;
import edu.hw2.task2.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {
    @Test
    @DisplayName("площадь прямоугольника")
    void Area_ShouldReturnTwelve() {
        Rectangle rect = new Rectangle(10, 20);
        int ans = rect.setWidth(4).setHeight(3).area();
        assertThat(ans).isEqualTo(12);
    }

    @Test
    @DisplayName("площадь квадрата")
    void Area_ShouldReturnEight() {
        Rectangle square = new Square(10);
        int ans = square.setHeight(2).setWidth(4).area();
        assertThat(ans).isEqualTo(8);

    }

}

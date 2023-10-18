package edu.hw2;

import edu.hw2.task2.RectangleImmutable;
import edu.hw2.task2.Rectangle;
import edu.hw2.task2.SquareImmutable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {
    @Test
    @DisplayName("площадь прямоугольника")
    void Area_ShouldReturnTwelve() {
        Rectangle rect = new RectangleImmutable(10, 20);
        rect.setWidth(4);
        rect.setHeight(3);
        assertThat(rect.area()).isEqualTo(12);
    }

    @Test
    @DisplayName("площадь квадрата")
    void Area_ShouldReturnEight() {
        Rectangle square = new SquareImmutable(10);
        square.setHeight(2);
        square.setWidth(4);
        assertThat(square.area()).isEqualTo(8);

    }

}

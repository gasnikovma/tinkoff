package edu.hw2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static edu.hw2.Task1.Expr.Constant;
import static edu.hw2.Task1.Expr.Negate;
import static edu.hw2.Task1.Expr.Addition;
import static edu.hw2.Task1.Expr.Multiplication;
import static edu.hw2.Task1.Expr.Exponent;

class Task1Test {
    @Test
    @DisplayName("Тест на константу")
    void Const_ShouldReturnValue() {
        var two = new Constant(2);
        assertEquals(2.0, two.evaluate());
    }

    @Test
    @DisplayName("Тест на отрицательное")
    void Negate_ShouldReturnValue() {
        var negOne = new Negate(new Constant(1));
        assertEquals(-1.0, negOne.evaluate());
    }

    @Test
    @DisplayName("Тест на добавление")
    void Addition_ShouldReturnValue() {
        var two = new Constant(2);
        var four = new Constant(4);
        var sumTwoFour = new Addition(two, four);
        assertEquals(6.0, sumTwoFour.evaluate());
    }

    @Test
    @DisplayName("Тест на умножение")
    void Multiplication_ShouldReturnValue() {
        var two = new Constant(2);
        var four = new Constant(4);
        var sumTwoFour = new Addition(two, four);
        var negOne = new Negate(new Constant(1));
        var mult = new Multiplication(sumTwoFour, negOne);
        assertEquals(-6.0, mult.evaluate());
    }

    @Test
    @DisplayName("Тест на экспоненту")
    void Exponent_ShouldReturnValue() {
        var two = new Constant(2);
        var four = new Constant(4);
        var negOne = new Negate(new Constant(1));
        var sumTwoFour = new Addition(two, four);
        var mult = new Multiplication(sumTwoFour, negOne);
        var exp = new Exponent(mult, 2);
        assertEquals(36.0, exp.evaluate());

    }

}

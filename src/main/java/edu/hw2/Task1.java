package edu.hw2;

public final class Task1 {
    private Task1() {

    }

    public sealed interface Expr {
        double evaluate();

        record Constant(double value) implements Expr {

            @Override
            public double evaluate() {
                return value;
            }
        }

        record Negate(Expr expr) implements Expr {

            @Override
            public double evaluate() {
                return -expr.evaluate();
            }
        }

        record Exponent(Expr expr, double pow) implements Expr {

            @Override
            public double evaluate() {
                return Math.pow(expr.evaluate(), pow);
            }
        }

        record Addition(Expr expr1, Expr expr2) implements Expr {

            @Override
            public double evaluate() {
                return expr1.evaluate() + expr2.evaluate();
            }
        }

        record Multiplication(Expr expr1, Expr expr2) implements Expr {

            @Override
            public double evaluate() {
                return expr1.evaluate() * expr2.evaluate();
            }
        }
    }
}

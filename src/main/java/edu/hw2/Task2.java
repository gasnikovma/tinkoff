package edu.hw2;

public class Task2 {
    public interface Shape {
        int area();
    }

    public static final class Rectangle implements Shape {
        private final int height;
        private final int width;

        public Rectangle(int rectangleHeight, int rectangleWidth) {
            this.height = rectangleHeight;
            this.width = rectangleWidth;
        }

        @Override
        public int area() {
            return width * height;
        }
    }

    public static final class Square implements Shape {
        private final int side;

        public Square(int squareSide) {
            this.side = squareSide;
        }

        @Override
        public int area() {
            return side * side;
        }
    }
}

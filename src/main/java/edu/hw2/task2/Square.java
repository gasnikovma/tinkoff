package edu.hw2.task2;

public class Square extends Rectangle {

    public Square(int width, int height) {
        super(width, height);
    }

    @Override
    public Square setWidth(int width) {
        return new Square(width, this.height);
    }

    @Override
    public Square setHeight(int height) {
        return new Square(this.width, height);
    }

    public Square(int side) {
        super(side, side);
    }

}

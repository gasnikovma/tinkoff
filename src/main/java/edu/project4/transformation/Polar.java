package edu.project4.transformation;

import edu.project4.models.Point;

public class Polar implements Transformation {
    private static final double C = 6;

    @Override
    public Point apply(Point point) {
        double squareRadius = Math.sqrt(point.x() * point.x() + point.y() * point.y());
        double t = Math.atan(point.x() / point.y());
        return new Point(C * t / Math.PI, squareRadius - 1);
    }
}

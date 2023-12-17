package edu.project4.transformation;

import edu.project4.models.Point;

public class Spiral implements Transformation {
    @Override
    public Point apply(Point point) {
        double squareRadius = Math.sqrt(point.x() * point.x() + point.y() * point.y());
        double t = Math.atan(point.x() / point.y());
        return new Point(1 / squareRadius * (Math.cos(t) + Math.sin(squareRadius)),
            1 / squareRadius * (Math.sin(t) - Math.cos(squareRadius)));
    }
}

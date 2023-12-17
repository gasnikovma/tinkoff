package edu.project4.transformation;

import edu.project4.models.Point;

public class Swirl implements Transformation {
    @Override
    public Point apply(Point point) {
        double squareRadius = Math.sqrt(point.x() * point.x() + point.y() * point.y());
        return new Point(
            point.x() * Math.sin(Math.pow(squareRadius, 2)) - point.y() * Math.cos(Math.pow(squareRadius, 2)),
            point.x() * Math.cos(Math.pow(squareRadius, 2)) + point.y() * Math.sin(Math.pow(squareRadius, 2))
        );
    }
}

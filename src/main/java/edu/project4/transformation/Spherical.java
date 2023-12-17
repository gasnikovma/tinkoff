package edu.project4.transformation;

import edu.project4.models.Point;

public class Spherical implements Transformation {
    @Override
    public Point apply(Point point) {
        double squareRadius = point.x() * point.x() + point.y() * point.y();
        return new Point(point.x() / squareRadius, point.y() / squareRadius);
    }
}

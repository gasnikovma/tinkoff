package edu.project4.transformation;

import edu.project4.models.Point;

public class Horseshoe implements Transformation {
    @Override
    public Point apply(Point point) {
        double squareRadius = Math.sqrt(point.x() * point.x() + point.y() * point.y());
        return new Point(Math.pow(point.x(), 2) - Math.pow(point.y(), 2) / squareRadius,
            2 * point.y() * point.x() / squareRadius);
    }
}

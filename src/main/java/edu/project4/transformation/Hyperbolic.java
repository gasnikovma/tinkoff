package edu.project4.transformation;

import edu.project4.models.Point;

public class Hyperbolic implements Transformation {

    @Override
    public Point apply(Point point) {
        double squareRadius = Math.sqrt(point.x() * point.x() + point.y() * point.y());
        double t = Math.atan(point.x() / point.y());
        return new Point(Math.sin(t) / squareRadius, squareRadius * Math.cos(t));
    }
}

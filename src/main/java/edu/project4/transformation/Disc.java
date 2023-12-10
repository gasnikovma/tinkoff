package edu.project4.transformation;

import edu.project4.models.Point;

public class Disc implements Transformation {
    @Override
    public Point apply(Point point) {
        double squareRadius = Math.sqrt(point.x() * point.x() + point.y() * point.y());
        double t = Math.atan(point.x() / point.y());
        return new Point(t / squareRadius * Math.sin(Math.PI * squareRadius),
            t / squareRadius * Math.cos(Math.PI * squareRadius));
    }
}

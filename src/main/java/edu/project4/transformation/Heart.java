package edu.project4.transformation;

import edu.project4.models.Point;

public class Heart implements Transformation {
    private static final double C = 0.5;

    @Override
    public Point apply(Point point) {
        double squareRadius = Math.sqrt(point.x() * point.x() + point.y() * point.y());
        double t = Math.atan(point.x() / point.y());
        return new Point(
            C * squareRadius * Math.sin(t * squareRadius),
            C * (-1) * squareRadius * Math.cos(t * squareRadius)
        );

    }
}

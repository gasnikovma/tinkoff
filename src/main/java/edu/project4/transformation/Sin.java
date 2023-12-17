package edu.project4.transformation;

import edu.project4.models.Point;

public class Sin implements Transformation {
    private static final double C = 1.5;

    @Override
    public Point apply(Point point) {
        return new Point(C * Math.sin(point.x()), C * Math.sin(point.y()));
    }
}

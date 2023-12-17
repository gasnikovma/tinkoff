package edu.project4.transformation;

import edu.project4.models.Point;

public record Shift(double a, double b, double c, double d, double e, double f) implements Transformation {
    @Override
    public Point apply(Point point) {
        return new Point(a * point.x() + b * point.y() + c, d * point.x() + e * point.y() + f);
    }
}

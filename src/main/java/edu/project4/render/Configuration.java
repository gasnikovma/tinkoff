package edu.project4.render;

import edu.project4.models.Point;
import edu.project4.transformation.Variation;
import java.util.Random;

public class Configuration {
    private Configuration() {

    }

    public static final double XLIMIT = 1.777;
    public static final double YLIMIT = 1;
    public static final int WIDTH = 1920 * 2;
    public static final int HEIGHT = 1080 * 2;
    public static final int SAMPLES = 1000000;
    public static final short ITERPERSAMP = 100;
    private static final Random RANDOM = new Random();

    public static Point randPoint() {
        double x = RANDOM.nextDouble(XLIMIT * (-1), XLIMIT);
        double y = RANDOM.nextDouble(YLIMIT * (-1), YLIMIT);
        return new Point(x, y);
    }

    public static Point transform(Point point, Variation variation) {
        return variation.func().apply(variation.shift().apply(point));
    }

    public static Point rotate(Point point, double theta2) {
        return new Point(
            point.x() * Math.cos(theta2) - point.y() * Math.sin(theta2),
            point.x() * Math.sin(theta2) + point.y() * Math.cos(theta2)
        );
    }

}

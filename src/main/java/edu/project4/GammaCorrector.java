package edu.project4;

import edu.project4.models.FractalImage;
import edu.project4.models.Pixel;

public class GammaCorrector {
    private static final double GAMMA = 2.2;

    void correction(FractalImage image) {
        double max = 0;
        for (int row = 0; row < image.width(); row++) {
            for (int col = 0; col < image.height(); col++) {
                Pixel cur = image.getPixel(row, col);
                if (cur.hitCount() != 0) {
                    image.setPixel(new Pixel(
                        cur.red(),
                        cur.green(),
                        cur.blue(),
                        Math.log10(cur.hitCount()),
                        cur.hitCount()
                    ), row, col);
                }
                max = Math.max(image.getPixel(row, col).normal(), max);
            }

        }
        for (int row = 0; row < image.width(); row++) {
            for (int col = 0; col < image.height(); col++) {
                Pixel cur = image.getPixel(row, col);
                double newNormal = cur.normal() / max;
                image.setPixel(new Pixel(
                    cur.red() * Math.pow(newNormal, (1 / GAMMA)),
                    cur.green() * Math.pow(newNormal, (1 / GAMMA)),
                    cur.blue() * Math.pow(newNormal, (1 / GAMMA)),
                    newNormal,
                    cur.hitCount()
                ), row, col);
            }
        }

    }
}

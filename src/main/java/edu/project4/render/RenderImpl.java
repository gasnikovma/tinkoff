package edu.project4.render;

import edu.project4.models.FractalImage;
import edu.project4.models.Pixel;
import edu.project4.models.Point;
import edu.project4.transformation.Variation;
import java.util.List;
import java.util.Random;
import static edu.project4.render.Configuration.XLIMIT;
import static edu.project4.render.Configuration.YLIMIT;
import static edu.project4.render.Configuration.randPoint;
import static edu.project4.render.Configuration.rotate;
import static edu.project4.render.Configuration.transform;

public class RenderImpl implements Render {
    private final static Random RANDOM = new Random();

    @Override
    public FractalImage render(
        FractalImage canvas,
        List<Variation> variations,
        int symmetry,
        int samples,
        short iterPerSample
    ) {
        for (int i = 0; i < samples; i++) {
            Point point = randPoint();
            for (int iter = 0; iter < iterPerSample; iter++) {
                Variation variation = variations.get(RANDOM.nextInt(variations.size()));
                point = transform(point, variation);
                double theta2 = 0d;
                for (int s = 0; s < symmetry; theta2 += Math.PI * 2 / symmetry, s++) {
                    Point rotated = rotate(point, theta2);
                    int newX = (int) (canvas.width() - (XLIMIT - rotated.x()) / (XLIMIT * 2) * canvas.width());
                    int newY = (int) (canvas.height() - (YLIMIT - rotated.y()) / (YLIMIT * 2) * canvas.height());
                    Pixel pixel = canvas.getPixel(newX, newY);
                    if (pixel == null) {
                        continue;
                    }
                    if (pixel.hitCount() == 0) {
                        canvas.setPixel(
                            new Pixel(variation.red(), variation.green(), variation.blue(), 0, 1),
                            newX,
                            newY
                        );
                    } else {
                        canvas.setPixel(new Pixel(
                            (variation.red() + pixel.red()) / 2,
                            (variation.green() + pixel.green()) / 2,
                            (variation.blue() + pixel.blue()) / 2,
                            pixel.normal(),
                            pixel.hitCount() + 1
                        ), newX, newY);
                    }

                }

            }
        }
        return canvas;
    }

}

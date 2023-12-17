package edu.project4.render;

import edu.project4.Session;
import edu.project4.models.FractalImage;
import edu.project4.models.Pixel;
import edu.project4.models.Point;
import edu.project4.transformation.Diamond;
import edu.project4.transformation.Polar;
import edu.project4.transformation.Transformation;
import edu.project4.transformation.Variation;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ExecutionException;
import static edu.project4.Session.getRandVariations;
import static org.junit.jupiter.api.Assertions.*;

class RenderImplTest {

    @Test
    void renderSingleThread() throws ExecutionException, InterruptedException {
        FractalImage canvas = FractalImage.create(25, 25);
        Render render = new RenderImpl();
        Transformation transformation = new Polar();

        render.render(canvas, getRandVariations(transformation), 1, 1500, (short) 200);

        for (int x = 0; x < canvas.width(); ++x) {
            for (int y = 0; y < canvas.height(); ++y) {
                Pixel pixel = canvas.getPixel(x, y);
                assertTrue(pixel.red() >= 0 && pixel.red() <= 255);
                assertTrue(pixel.green() >= 0 && pixel.green() <= 255);
                assertTrue(pixel.blue() >= 0 && pixel.blue() <= 255);

            }
        }
    }
    @Test
    void renderMultiThread() throws ExecutionException, InterruptedException {
        FractalImage canvas = FractalImage.create(25, 25);
        Render render = new MultiThreadRenderImpl();
        Transformation transformation = new Polar();

        render.render(canvas, getRandVariations(transformation), 1, 1500, (short) 200);

        for (int x = 0; x < canvas.width(); ++x) {
            for (int y = 0; y < canvas.height(); ++y) {
                Pixel pixel = canvas.getPixel(x, y);
                assertTrue(pixel.red() >= 0 && pixel.red() <= 255);
                assertTrue(pixel.green() >= 0 && pixel.green() <= 255);
                assertTrue(pixel.blue() >= 0 && pixel.blue() <= 255);

            }
        }
    }

}

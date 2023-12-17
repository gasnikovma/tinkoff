package edu.project4;

import edu.project4.models.FractalImage;
import edu.project4.render.Render;
import edu.project4.transformation.Shift;
import edu.project4.transformation.Transformation;
import edu.project4.transformation.Variation;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import javax.imageio.ImageIO;
import static edu.project4.render.Configuration.HEIGHT;
import static edu.project4.render.Configuration.ITERPERSAMP;
import static edu.project4.render.Configuration.SAMPLES;
import static edu.project4.render.Configuration.WIDTH;

@SuppressWarnings("checkstyle:MagicNumber")
public class Session {
    private static final Random RANDOM = new Random();

    public void run(Render render, Transformation transformation, String fileName)
        throws IOException, ExecutionException, InterruptedException {
        FractalImage canvas = FractalImage.create(WIDTH, HEIGHT);
        render.render(canvas, getRandVariations(transformation), 1, SAMPLES, ITERPERSAMP);
        GammaCorrector gammaCorrector = new GammaCorrector();
        gammaCorrector.correction(canvas);
        BufferedImage img = new BufferedImage(canvas.width(), canvas.height(), BufferedImage.TYPE_INT_ARGB);
        for (int row = 0; row < canvas.width(); row++) {
            for (int col = 0; col < canvas.height(); col++) {
                if (canvas.getPixel(row, col).hitCount() == 0) {
                    img.setRGB(row, col, 255 << 24);
                } else {
                    img.setRGB(
                        row,
                        col,
                        blend(
                            canvas.getPixel(row, col).red(),
                            canvas.getPixel(row, col).green(),
                            canvas.getPixel(row, col).blue()
                        )
                    );

                }
            }
        }
        File outputFile = new File(fileName);
        ImageIO.write(img, "PNG", outputFile);

    }

    public static List<Variation> getRandVariations(Transformation transformation) {
        List<Variation> variations = new ArrayList<>(20);
        int iter = 0;
        while (iter < 20) {
            Color color = new Color(RANDOM.nextInt(256), RANDOM.nextInt(256), RANDOM.nextInt(256));
            Shift shift = getShift();
            variations.add(new Variation(transformation, shift, color.getRed(), color.getGreen(), color.getBlue()));
            iter += 1;
        }
        return variations;

    }

    public static Shift getShift() {
        boolean flag = false;
        double a = 0;
        double b = 0;
        double d = 0;
        double e = 0;
        while (!flag) {
            a = RANDOM.nextDouble(-1, 1);
            b = RANDOM.nextDouble(-1, 1);
            d = RANDOM.nextDouble(-1, 1);
            e = RANDOM.nextDouble(-1, 1);
            if (Math.pow(a, 2) + Math.pow(d, 2) < 1 && Math.pow(b, 2) + Math.pow(e, 2) < 1
                &&
                (Math.pow(a, 2) + Math.pow(b, 2) + Math.pow(d, 2) + Math.pow(e, 2) < 1 + Math.pow(a * e - b * d, 2))) {
                flag = true;
            }
        }
        double c = RANDOM.nextDouble(-2, 2);
        double f = RANDOM.nextDouble(-2, 2);
        return new Shift(a, b, c, d, e, f);
    }

    public static int blend(double red, double green, double blue) {

        return 255 << 24 | (int) red << 16 | (int) green << 8 | (int) blue;
    }
}

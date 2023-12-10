package edu.project4.render;

import edu.project4.models.FractalImage;
import edu.project4.models.Pixel;
import edu.project4.models.Point;
import edu.project4.transformation.Variation;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.logging.log4j.LogManager;
import static edu.project4.render.Configuration.XLIMIT;
import static edu.project4.render.Configuration.YLIMIT;
import static edu.project4.render.Configuration.randPoint;
import static edu.project4.render.Configuration.rotate;
import static edu.project4.render.Configuration.transform;

public class MultiThreadRenderImpl implements Render {
    private static final Lock LOCK = new ReentrantLock();
    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();
    private static final int TIMEOUT = 100;

    @Override
    public FractalImage render(
        FractalImage canvas,
        List<Variation> variations,
        int symmetry,
        int samples,
        short iterPerSample
    ) throws InterruptedException {
        int numOfThreads = Runtime.getRuntime().availableProcessors();

        ExecutorService executorService = Executors.newWorkStealingPool(numOfThreads);
        for (int i = 0; i < samples; i++) {
            Point point = randPoint();
            executorService.submit(() -> {
                for (int j = 0; j < numOfThreads; ++j) {
                    sampleWork(point, variations, symmetry, canvas, iterPerSample / numOfThreads);
                }

            });
        }
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(TIMEOUT, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
        return canvas;
    }

    private void sampleWork(Point point, List<Variation> variations, int symmetry, FractalImage canvas, int iterCnt) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int iter = 0; iter < iterCnt; iter++) {
            Variation variation = variations.get(random.nextInt(variations.size()));
            Point point1 = point;
            point1 = transform(point1, variation);
            double theta2 = 0d;
            for (int s = 0; s < symmetry; theta2 += Math.PI * 2 / symmetry, s++) {
                Point rotated = rotate(point1, theta2);
                int newX = (int) (canvas.width() - (XLIMIT - rotated.x()) / (XLIMIT * 2) * canvas.width());
                int newY = (int) (canvas.height() - (YLIMIT - rotated.y()) / (YLIMIT * 2) * canvas.height());
                LOCK.lock();
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
                    LOCK.unlock();
                } else {
                    canvas.setPixel(new Pixel(
                        (variation.red() + pixel.red()) / 2,
                        (variation.green() + pixel.green()) / 2,
                        (variation.blue() + pixel.blue()) / 2,
                        pixel.normal(),
                        pixel.hitCount() + 1
                    ), newX, newY);
                    LOCK.unlock();
                }

            }

        }
    }
}

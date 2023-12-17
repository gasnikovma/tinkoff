package edu.hw9;

import edu.hw9.task1.Metric;
import edu.hw9.task1.StatsCollector;
import org.junit.jupiter.api.Test;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StatsCollectorTest {

    @Test
    void StatsCollectorTest() throws InterruptedException, ExecutionException {
        StatsCollector collector = new StatsCollector();
        int threads = Runtime.getRuntime().availableProcessors();
        ExecutorService executors = Executors.newFixedThreadPool(threads);
        executors.submit(() -> {
            collector.push("metric1", new double[] {1.0, 2.4, 3.6, 3.0});
            collector.push("metric2", new double[] {0.3, 1.5, 2.5, 6.0});
            collector.push("metric3", new double[] {1, 2, 3, 6});

        }).get();

        Collection<Metric> e = List.of(
            new Metric("metric1", 10.0, 2.5, 1.0, 3.6),
            new Metric("metric2", 10.3, 2.575, 0.3, 6.0),
            new Metric("metric3", 12.0, 3.0, 1.0, 6.0)
        );
        ConcurrentLinkedQueue<Metric> expected = new ConcurrentLinkedQueue<>(e);
        assertTrue(expected.containsAll(collector.stats()) && collector.stats().containsAll(expected));

    }
}

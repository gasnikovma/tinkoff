package edu.hw9.task1;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.concurrent.ConcurrentLinkedQueue;

public class StatsCollector {
    public StatsCollector() {

    }

    private final ConcurrentLinkedQueue<MetricType> metrics = new ConcurrentLinkedQueue<>();

    public void push(final String metricName, double[] values) {
        metrics.add(new MetricType(metricName, values));
    }

    public ConcurrentLinkedQueue<Metric> stats() {
        ConcurrentLinkedQueue<Metric> metricsData = new ConcurrentLinkedQueue<>();
        metrics.forEach(metrics -> {
            DoubleSummaryStatistics stat = Arrays.stream(metrics.val()).summaryStatistics();
            metricsData.add(new Metric(metrics.name(), stat.getSum(), stat.getAverage(), stat.getMin(), stat.getMax()));
        });
        return metricsData;

    }
}

package edu.project3.statistics;

import edu.project3.model.LogRecord;
import edu.project3.model.Statistics;

public class AverageResponseSize implements Counter<Long> {
    private static final String TITLE = "Average Response Size";
    private static final String RESOURCE = "Average Size";
    private static final String VALUE = "Value";

    private static long sum = 0;
    private static long count = 0;

    @Override public Statistics<Long> countStatistics() {
        if (count == 0) {
            return new Statistics<>(TITLE, RESOURCE, VALUE, 0L);
        } else {
            return new Statistics<>(TITLE, RESOURCE, VALUE, sum / count);
        }
    }

    @Override public void getMiddleCalc(LogRecord logRecord) {
        sum += logRecord.bodyBites();
        count += 1;
    }
}

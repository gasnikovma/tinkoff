package edu.project3.statistics;

import edu.project3.model.LogRecord;
import edu.project3.model.Statistics;
import java.util.List;

public class AverageResponseSize implements Counter<Double> {
    private static final String TITLE = "Average Response Size";
    private static final String RESOURCE = "Average Size";
    private static final String VALUE = "Value";

    @Override
    public Statistics<Double> countStatistics(List<LogRecord> logRecords) {
        return new Statistics<>(TITLE,
            RESOURCE,
            VALUE,
            logRecords.stream().mapToInt(LogRecord::bodyBites).average().orElse(0));
    }
}

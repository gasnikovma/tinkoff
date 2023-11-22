package edu.project3.statistics;

import edu.project3.model.LogRecord;
import edu.project3.model.Statistics;
import java.time.OffsetDateTime;

public class EarliestRequest implements Counter<OffsetDateTime> {
    private static final String TITLE = "EarliestRequest";
    private static final String RESOURCE = "Date";
    private static final String VALUE = "Value";

    private static OffsetDateTime offsetDateTime = OffsetDateTime.now();

    @Override
    public Statistics<OffsetDateTime> countStatistics() {
        return new Statistics<>(
            TITLE,
            RESOURCE,
            VALUE,
           offsetDateTime
        );
    }

    @Override
    public void getMiddleCalc(LogRecord logRecord) {
        if (offsetDateTime.isAfter(logRecord.localTime())) {
            offsetDateTime = logRecord.localTime();
        }

    }
}

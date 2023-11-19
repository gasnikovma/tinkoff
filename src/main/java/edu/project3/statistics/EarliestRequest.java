package edu.project3.statistics;

import edu.project3.model.LogRecord;
import edu.project3.model.Statistics;
import java.time.OffsetDateTime;
import java.util.Comparator;
import java.util.List;

public class EarliestRequest implements Counter<OffsetDateTime> {
    private static final String TITLE = "EarliestRequest";
    private static final String RESOURCE = "Date";
    private static final String VALUE = "Value";

    @Override
    public Statistics<OffsetDateTime> countStatistics(List<LogRecord> list) {
        return new Statistics<>(TITLE,
            RESOURCE,
            VALUE,
            list.stream().map(LogRecord::localTime).min(Comparator.naturalOrder()).orElse(null));
    }
}

package edu.project3.statistics;

import edu.project3.model.LogRecord;
import edu.project3.model.Statistics;
import java.util.List;

public interface Counter<T> {
    Statistics<T> countStatistics(List<LogRecord>logRecords);
}

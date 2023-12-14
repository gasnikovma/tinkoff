package edu.project3.statistics;

import edu.project3.model.LogRecord;
import edu.project3.model.Statistics;

public interface Counter<T> {
    Statistics<T> countStatistics();

    void getMiddleCalc(LogRecord logRecord);
}

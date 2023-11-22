package edu.project3.statistics;

import edu.project3.model.LogRecord;
import edu.project3.model.Statistics;

public class CountRequests implements Counter<Integer> {
    private static final String TITLE = "Total number of requests";
    private static final String RESOURCE = "Requests";
    private static final String VALUE = "Number of logs.txt";
    private static int counter = 0;

    @Override
    public Statistics<Integer> countStatistics() {
        return new Statistics<>(TITLE, RESOURCE, VALUE, counter);
    }

    @Override
    public void getMiddleCalc(LogRecord logRecord) {
        counter += 1;
    }
}

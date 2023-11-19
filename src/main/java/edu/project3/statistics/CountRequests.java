package edu.project3.statistics;

import edu.project3.model.LogRecord;
import edu.project3.model.Statistics;
import java.util.List;

public class CountRequests implements Counter<Integer> {
    private static final String TITLE = "Total number of requests";
    private static final String RESOURCE = "Requests";
    private static final String VALUE = "Number of logs.txt";

    @Override
    public Statistics<Integer> countStatistics(List<LogRecord> list) {
        return new Statistics<>(TITLE, RESOURCE, VALUE, list.size());
    }
}

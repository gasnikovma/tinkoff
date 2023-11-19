package edu.project3.statistics;

import edu.project3.model.LogRecord;
import edu.project3.model.Statistics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MostFrequentStatusCode implements Counter<Map<Integer,Integer>>{
    private static final String TITLE = "MostFrequentStatusCode";
    private static final String RESOURCE = "StatusCode";
    private static final String VALUE = "Quantity";

    @Override
    public Statistics<Map<Integer, Integer>> countStatistics(List<LogRecord> logRecords) {
        Map<Integer, Integer> countStatusCode = new HashMap<>();
        for (int i = 0; i < logRecords.size(); i++) {
            Integer statusCode=logRecords.get(i).status();
            countStatusCode.put(statusCode, countStatusCode.getOrDefault(statusCode, 0) + 1);
        }
        return new Statistics<>(TITLE, RESOURCE, VALUE, countStatusCode);
    }
}

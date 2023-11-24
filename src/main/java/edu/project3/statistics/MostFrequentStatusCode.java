package edu.project3.statistics;

import edu.project3.model.LogRecord;
import edu.project3.model.Statistics;
import java.util.Map;

public class MostFrequentStatusCode implements Counter<Map<Integer, Integer>> {
    private static final String TITLE = "MostFrequentStatusCode";
    private static final String RESOURCE = "StatusCode";
    private static final String VALUE = "Quantity";
    private Map<Integer, Integer> frequencyOfStatusCode;

    public MostFrequentStatusCode(Map<Integer, Integer> frequencyOfStatusCode) {
        this.frequencyOfStatusCode = frequencyOfStatusCode;
    }

    @Override
    public Statistics<Map<Integer, Integer>> countStatistics() {
        return new Statistics<>(TITLE, RESOURCE, VALUE, frequencyOfStatusCode);
    }

    @Override
    public void getMiddleCalc(LogRecord logRecord) {
        Integer statusCode = logRecord.status();
        frequencyOfStatusCode.put(statusCode, frequencyOfStatusCode.getOrDefault(statusCode, 0) + 1);

    }
}

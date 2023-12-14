package edu.project3.statistics;

import edu.project3.model.LogRecord;
import edu.project3.model.Statistics;
import java.util.Map;

public class MostFrequentResource implements Counter<Map<String, Integer>> {
    private static final String TITLE = "MostFrequentResource";
    private static final String RESOURCE = "Resource";
    private static final String VALUE = "Quantity";

    private final Map<String, Integer> frequencyOfResources;

    public MostFrequentResource(Map<String, Integer> frequencyOfResources) {
        this.frequencyOfResources = frequencyOfResources;
    }

    @Override
    public Statistics<Map<String, Integer>> countStatistics() {
        return new Statistics<>(TITLE, RESOURCE, VALUE, frequencyOfResources);
    }

    @Override
    public void getMiddleCalc(LogRecord logRecord) {
        String resource = logRecord.request().split(" ")[1];
        frequencyOfResources.put(resource, frequencyOfResources.getOrDefault(resource, 0) + 1);

    }
}

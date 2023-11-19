package edu.project3.statistics;

import edu.project3.model.LogRecord;
import edu.project3.model.Statistics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MostFrequentResource implements Counter<Map<String, Integer>> {
    private static final String TITLE = "MostFrequentResource";
    private static final String RESOURCE = "Resource";
    private static final String VALUE = "Quantity";

    @Override
    public Statistics<Map<String, Integer>> countStatistics(List<LogRecord> logRecords) {
        Map<String, Integer> countResources = new HashMap<>();
        for (int i = 0; i < logRecords.size(); i++) {
            String resource = logRecords.get(i).request().split(" ")[1];
            countResources.put(resource, countResources.getOrDefault(resource, 0) + 1);
        }
        return new Statistics<>(TITLE, RESOURCE, VALUE, countResources);
    }
}

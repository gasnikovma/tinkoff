package edu.project3.statistics;

import edu.project3.model.LogRecord;
import edu.project3.model.Statistics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MostFrequentUserAgent implements Counter<Map<String, Integer>> {
    private static final String TITLE = "MostFrequentUserAgent";
    private static final String RESOURCE = "UserAgent";
    private static final String VALUE = "Quantity";

    @Override public Statistics<Map<String, Integer>> countStatistics(List<LogRecord> logRecords) {
        Map<String, Integer> countUserAgent = new HashMap<>();
        for (int i = 0; i < logRecords.size(); i++) {
            if (logRecords.get(i).httpUserAgent() != null) {
                String[] httpUserAgent = logRecords.get(i).httpUserAgent().split(" ");
                for (int j = 0; j < httpUserAgent.length; j++) {
                    if (!Objects.equals(httpUserAgent[j].charAt(0), '(')
                        && !Objects.equals(httpUserAgent[j].charAt(httpUserAgent[j].length() - 1), ')')
                        && httpUserAgent[j].contains("/")) {
                        String agent = httpUserAgent[j].substring(0, httpUserAgent[j].indexOf("/"));
                        countUserAgent.put(agent, countUserAgent.getOrDefault(agent, 0) + 1);
                    }
                }
            }
        }
        return new Statistics<>(TITLE, RESOURCE, VALUE, countUserAgent);
    }
}

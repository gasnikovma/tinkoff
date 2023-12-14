package edu.project3.statistics;

import edu.project3.model.LogRecord;
import edu.project3.model.Statistics;
import java.util.Map;
import java.util.Objects;

public class MostFrequentUserAgent implements Counter<Map<String, Integer>> {
    private static final String TITLE = "MostFrequentUserAgent";
    private static final String RESOURCE = "UserAgent";
    private static final String VALUE = "Quantity";
    private Map<String, Integer> frequencyOfUserAgent;

    public MostFrequentUserAgent(Map<String, Integer> frequencyOfUserAgent) {
        this.frequencyOfUserAgent = frequencyOfUserAgent;
    }

    @Override public Statistics<Map<String, Integer>> countStatistics() {
        return new Statistics<>(TITLE, RESOURCE, VALUE, frequencyOfUserAgent);
    }

    @Override public void getMiddleCalc(LogRecord logRecord) {
        if (logRecord.httpUserAgent() != null) {
            String[] httpUserAgent = logRecord.httpUserAgent().split(" ");
            for (int j = 0; j < httpUserAgent.length; j++) {
                if (!Objects.equals(httpUserAgent[j].charAt(0), '(')
                    && !Objects.equals(httpUserAgent[j].charAt(httpUserAgent[j].length() - 1), ')')
                    && httpUserAgent[j].contains("/")) {
                    String agent = httpUserAgent[j].substring(0, httpUserAgent[j].indexOf("/"));
                    frequencyOfUserAgent.put(agent, frequencyOfUserAgent.getOrDefault(agent, 0) + 1);
                }
            }
        }

    }
}

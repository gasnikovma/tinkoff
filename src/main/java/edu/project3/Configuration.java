package edu.project3;

import edu.project3.statistics.AverageResponseSize;
import edu.project3.statistics.CountRequests;
import edu.project3.statistics.Counter;
import edu.project3.statistics.EarliestRequest;
import edu.project3.statistics.MostFrequentResource;
import edu.project3.statistics.MostFrequentStatusCode;
import edu.project3.statistics.MostFrequentUserAgent;
import java.util.List;

public class Configuration {

    private Configuration() {

    }

    public static final List<Counter> STATISTICS = List.of(
        new AverageResponseSize(),
        new CountRequests(),
        new EarliestRequest(),
        new MostFrequentResource(),
        new MostFrequentStatusCode(),
        new MostFrequentUserAgent()
    );
    public static final String FILE_FOR_DOWNLOAD = "src/main/java/edu/project3/download/data.txt";
    public static final String TEST = "src/main/java/edu/project3/download/test.txt";

}

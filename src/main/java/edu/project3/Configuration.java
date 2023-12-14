package edu.project3;

import edu.project3.statistics.AverageResponseSize;
import edu.project3.statistics.CountRequests;
import edu.project3.statistics.Counter;
import edu.project3.statistics.EarliestRequest;
import edu.project3.statistics.MostFrequentResource;
import edu.project3.statistics.MostFrequentStatusCode;
import edu.project3.statistics.MostFrequentUserAgent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.List;

public class Configuration {

    private Configuration() {

    }

    public static final List<Counter> STATISTICS = List.of(
        new AverageResponseSize(),
        new CountRequests(),
        new EarliestRequest(),
        new MostFrequentResource(new HashMap<>()),
        new MostFrequentStatusCode(new HashMap<>()),
        new MostFrequentUserAgent(new HashMap<>())
    );
    public static final OffsetDateTime DEFAULT_FROM =
        OffsetDateTime.of(LocalDate.of(2014, 11, 10), LocalTime.of(10, 20, 30), ZoneOffset.UTC);
    public static final OffsetDateTime DEFAULT_TO = OffsetDateTime.now();
    public static final String FILE_FOR_DOWNLOAD = "src/main/java/edu/project3/download/data.txt";
    public static final String TEST = "src/main/java/edu/project3/download/test.txt";

}

package edu.project3;

import edu.project3.model.LogRecord;
import edu.project3.model.Statistics;
import edu.project3.statistics.AverageResponseSize;
import edu.project3.statistics.CountRequests;
import edu.project3.statistics.Counter;
import edu.project3.statistics.EarliestRequest;
import edu.project3.statistics.MostFrequentResource;
import edu.project3.statistics.MostFrequentStatusCode;
import edu.project3.statistics.MostFrequentUserAgent;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class CounterTest {

    @Test
    void AverageResponseSizeTest() throws IOException, InterruptedException {
        Parser parser = new Parser();
        List<LogRecord> logRecords = parser.parse(Configuration.TEST).toList();
        Counter<Double> counter = new AverageResponseSize();
        Statistics<Double> get = counter.countStatistics(logRecords);
        assertEquals(get.result(), 3760385.714285714);

    }

    @Test
    void CountRequestsTest() throws IOException, InterruptedException {
        Parser parser = new Parser();
        List<LogRecord> logRecords = parser.parse(Configuration.TEST).toList();
        Counter<Integer> counter = new CountRequests();
        Statistics<Integer> get = counter.countStatistics(logRecords);
        assertEquals(get.result(), 7);

    }

    @Test
    void EarliestRequestTest() throws IOException, InterruptedException {
        Parser parser = new Parser();
        List<LogRecord> logRecords = parser.parse(Configuration.TEST).toList();
        Counter<OffsetDateTime> counter = new EarliestRequest();
        Statistics<OffsetDateTime> get = counter.countStatistics(logRecords);
        assertEquals(
            get.result(),
            OffsetDateTime.of(LocalDate.of(2015, Month.MAY, 17), LocalTime.of(8, 5, 3), ZoneOffset.UTC)
        );

    }

    @Test
    void MostFrequentResourceTest() throws IOException, InterruptedException {
        Parser parser = new Parser();
        List<LogRecord> logRecords = parser.parse(Configuration.TEST).toList();
        Counter<Map<String, Integer>> counter = new MostFrequentResource();
        Statistics<Map<String, Integer>> get = counter.countStatistics(logRecords);
        Map<String, Integer> ans = get.result();
        assertEquals(ans.get("/downloads/product_1"), 4);
        assertEquals(ans.get("/downloads/product_2"), 3);

    }

    @Test
    void MostFrequentStatusCodeTest() throws IOException, InterruptedException {
        Parser parser = new Parser();
        List<LogRecord> logRecords = parser.parse(Configuration.TEST).toList();
        Counter<Map<Integer, Integer>> counter = new MostFrequentStatusCode();
        Statistics<Map<Integer, Integer>> get = counter.countStatistics(logRecords);
        Map<Integer, Integer> ans = get.result();
        assertEquals(ans.get(304), 1);
        assertEquals(ans.get(200), 4);
        assertEquals(ans.get(404), 2);

    }

    @Test
    void MostFrequentUserAgentTest() throws IOException, InterruptedException {
        Parser parser = new Parser();
        List<LogRecord> logRecords = parser.parse(Configuration.TEST).toList();
        Counter<Map<String, Integer>> counter = new MostFrequentUserAgent();
        Statistics<Map<String, Integer>> get = counter.countStatistics(logRecords);
        Map<String, Integer> ans = get.result();
        assertEquals(ans.get("APT-HTTP"), 2);
        assertEquals(ans.get("Go"), 1);
        assertEquals(ans.get("urlgrabber"), 2);
        assertEquals(ans.get("Mozilla"), 1);

    }

}

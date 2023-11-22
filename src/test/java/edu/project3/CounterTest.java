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

    @Test <T> void StatisticsTest() throws IOException, InterruptedException {
        Parser parser = new Parser();
        List<Statistics<T>> statistics =
            parser.parse(Configuration.TEST, Configuration.DEFAULT_FROM, Configuration.DEFAULT_TO);

        for (int i = 0; i < statistics.size(); i++) {
            if (statistics.get(i).title().equals("Average Response Size")) {
                assertEquals(statistics.get(i).result(), 3760385L);
            } else if (statistics.get(i).title().equals("Total number of requests")) {
                assertEquals(statistics.get(i).result(), 7);
            } else if (statistics.get(i).title().equals("EarliestRequest")) {
                assertEquals(
                    statistics.get(i).result(),
                    OffsetDateTime.of(LocalDate.of(2015, Month.MAY, 17), LocalTime.of(8, 5, 3), ZoneOffset.UTC)
                );
            } else if (statistics.get(i).title().equals("MostFrequentResource")) {
                Map<String, Integer> ans = (Map<String, Integer>) statistics.get(i).result();
                assertEquals(ans.get("/downloads/product_1"), 4);
                assertEquals(ans.get("/downloads/product_2"), 3);
            } else if (statistics.get(i).title().equals("MostFrequentStatusCode")) {
                Map<Integer, Integer> ans = (Map<Integer, Integer>) statistics.get(i).result();
                assertEquals(ans.get(304), 1);
                assertEquals(ans.get(200), 4);
                assertEquals(ans.get(404), 2);
            } else if (statistics.get(i).title().equals("MostFrequentUserAgent")) {
                Map<String, Integer> ans = (Map<String, Integer>) statistics.get(i).result();
                assertEquals(ans.get("APT-HTTP"), 2);
                assertEquals(ans.get("Go"), 1);
                assertEquals(ans.get("urlgrabber"), 2);
                assertEquals(ans.get("Mozilla"), 1);
            }

        }

    }

}

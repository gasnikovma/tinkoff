package edu.project3;

import edu.project3.model.LogRecord;
import edu.project3.model.Statistics;
import edu.project3.printer.MarkdownPrinter;
import edu.project3.printer.Printer;
import edu.project3.statistics.Counter;
import edu.project3.statistics.EarliestRequest;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.stream.Stream;

public class Main {
    private Main(){

    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Parser parser = new Parser();
        Stream<LogRecord>logRecords = parser.parse(
            "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs");
        Counter<OffsetDateTime> counter = new EarliestRequest();
        Statistics<OffsetDateTime> offsetDateTimeStatistics =counter.countStatistics(logRecords.toList());
        Printer printer = new MarkdownPrinter();
      System.out.println( printer.print(offsetDateTimeStatistics));


    }
}

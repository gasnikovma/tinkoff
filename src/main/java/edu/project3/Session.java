package edu.project3;

import edu.project3.model.LogRecord;
import edu.project3.printer.Printer;
import edu.project3.printer.PrinterFactory;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Session {
    private static final String PATH = "--path";
    private static final String FROM = "--from";
    private static final String TO = "--to";
    private static final String FORMAT = "--format";
    private static final String MARKDOWN = "markdown";
    private static final Logger LOGGER = LogManager.getLogger();
    private final Parser parser;
    private final PrinterFactory printerFactory;

    private void print(String s) {
        LOGGER.info("\n" + s);
    }

    Session(Parser parser, PrinterFactory printerFactory) {
        this.parser = parser;
        this.printerFactory = printerFactory;
    }

    public void startSession(String[] args) throws IOException, InterruptedException {
        String path = null;
        OffsetDateTime fromDate = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        OffsetDateTime toDate = null;
        String format = "adoc";
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals(PATH)) {
                path = args[i + 1];
            } else if (args[i].equals(FROM)) {
                fromDate = OffsetDateTime.parse(args[i + 1], formatter);
            } else if (args[i].equals(TO)) {
                toDate = OffsetDateTime.parse(args[i + 1], formatter);
            } else if (args[i].equals(FORMAT)) {
                format = args[i + 1];
            }

        }
        if (path == null) {
            throw new IllegalArgumentException();
        }
        Stream<LogRecord> logRecords = parser.parse(path);
        List<LogRecord> logs = new ArrayList<>(logRecords.toList());
        if (fromDate != null) {
            OffsetDateTime from = fromDate;
            logs = logRecords.filter(x -> x.localTime().isAfter(from)).toList();
        }
        if (toDate != null) {
            OffsetDateTime to = toDate;
            logs = logRecords.filter(x -> x.localTime().isAfter(to)).toList();
        }
        if (Objects.equals(format, MARKDOWN)) {
            format = MARKDOWN;
        }
        List<LogRecord> finalLogs = logs;
        Printer printer = printerFactory.createPrinter(format);
        Configuration.STATISTICS.stream()
            .forEach(statisticsLogs -> print(printer.print(statisticsLogs.countStatistics(finalLogs))));

    }
}

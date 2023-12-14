package edu.project3;

import edu.project3.model.Statistics;
import edu.project3.printer.Printer;
import edu.project3.printer.PrinterFactory;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Session {
    private static final String PATH = "--path";
    private static final String FROM = "--from";
    private static final String TO = "--to";
    private static final String FORMAT = "--format";
    private static final String MARKDOWN = "markdown";
    private static final String ASCII = "adoc";
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

    public <T> void startSession(String[] args) throws IOException, InterruptedException {
        String path = null;
        OffsetDateTime fromDate = null;
        DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd").parseDefaulting(
            ChronoField.NANO_OF_DAY, 0).parseDefaulting(ChronoField.OFFSET_SECONDS, 0).toFormatter();
        OffsetDateTime toDate = null;
        String format = null;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals(PATH)) {
                path = args[i + 1];
            } else if (args[i].equals(FROM)) {
                fromDate = OffsetDateTime.parse(args[i + 1], formatter);
            } else if (args[i].equals(TO)) {
                toDate = OffsetDateTime.parse(args[i + 1], formatter);
            } else if (args[i].equals(FORMAT)) {
                if (args[i + 1].equals(MARKDOWN) || args[i + 1].equals(ASCII)) {
                    format = args[i + 1];
                }
            }

        }
        if (path == null) {
            throw new IllegalArgumentException();
        }
        if (format == null) {
            throw new IllegalArgumentException();
        }
        if (fromDate == null) {
            fromDate = Configuration.DEFAULT_FROM;
        }
        if (toDate == null) {
            toDate = Configuration.DEFAULT_TO;
        }
        List<Statistics<T>> statistics = parser.parse(path, fromDate, toDate);
        Printer printer = printerFactory.createPrinter(format);
        for (int i = 0; i < statistics.size(); i++) {
            print(printer.print(statistics.get(i)));
        }

    }
}

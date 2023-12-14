package edu.project3;

import edu.project3.api.LogsApi;
import edu.project3.model.LogRecord;
import edu.project3.model.Statistics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Parser {

    public static final String FILE_FOR_DOWNLOAD = "src/main/java/edu/project3/download/data.txt";
    public static final String NULL = "-";

    public <T> List<Statistics<T>> parse(String string, OffsetDateTime from, OffsetDateTime to)
        throws IOException, InterruptedException {
        List<Statistics<T>> statistics;
        String urlPattern = "^(https?|http)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

        Pattern pattern = Pattern.compile(urlPattern);
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            Path of = Path.of(FILE_FOR_DOWNLOAD);
            if (!Files.exists(of)) {
                Files.createFile(of);
            }
            URI uri = URI.create(string);
            LogsApi.getNginxLogs(uri, FILE_FOR_DOWNLOAD);
            try (BufferedReader reader = new BufferedReader(new FileReader(of.toFile()))) {
                while (reader.ready()) {
                    LogRecord log = parseLog(reader.readLine(), from, to);
                    if (log != null) {
                        for (int i = 0; i < Configuration.STATISTICS.size(); i++) {
                            Configuration.STATISTICS.get(i).getMiddleCalc(log);
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                Path directory = Path.of(string);
                List<String> files = Files.readAllLines(directory);
                for (int i = 0; i < files.size(); i++) {
                    LogRecord log = parseLog(files.get(i), from, to);
                    if (log != null) {
                        for (int j = 0; j < Configuration.STATISTICS.size(); j++) {
                            Configuration.STATISTICS.get(j).getMiddleCalc(log);
                        }
                    }

                }
            } catch (NoSuchFileException exception) {
                throw new IllegalArgumentException("Invalid Path or URI", exception);
            }
        }

        statistics = new ArrayList<>();

        for (int i = 0; i < Configuration.STATISTICS.size(); i++) {
            Statistics<T> res = Configuration.STATISTICS.get(i).countStatistics();
            statistics.add(res);
        }
        return statistics;

    }

    @SuppressWarnings("checkstyle:MagicNumber")
    private LogRecord parseLog(String log, OffsetDateTime from, OffsetDateTime to) {
        String[] elements = log.split(" ");
        String address = elements[0];
        String user = elements[2].equals("-") ? null : elements[2];
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ssZ", Locale.ENGLISH);
        OffsetDateTime offsetDateTime =
            OffsetDateTime.parse(elements[3].substring(1).concat(elements[4].substring(0, 5)), dateTimeFormatter);
        if (offsetDateTime.isBefore(from) || offsetDateTime.isAfter(to)) {
            return null;
        }
        String request = elements[5].concat(" ").concat(elements[6]).concat(" ").concat(elements[7])
            .substring(1, elements[5].length() + elements[6].length() + elements[7].length());
        Integer status = Integer.parseInt(elements[8]);
        Integer bodyBites = Integer.parseInt(elements[9]);
        String httRefer = elements[10].equals(NULL) ? null : elements[10];
        StringBuilder userAgentBuilder = new StringBuilder();
        for (int i = 11; i < elements.length - 1; i++) {
            userAgentBuilder.append(elements[i]).append(" ");
        }
        userAgentBuilder.append(elements[elements.length - 1]);
        String userAgent = elements[11].equals(NULL) ? null : String.valueOf(userAgentBuilder);
        String httpUserAgent = null;
        if (userAgent != null) {
            httpUserAgent = userAgent.substring(1, userAgent.length() - 1);
        }
        return new LogRecord(address, user, offsetDateTime, request, status, bodyBites, httRefer, httpUserAgent);

    }

}

package edu.project3;

import edu.project3.api.LogsApi;
import edu.project3.model.LogRecord;
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
import java.util.stream.Stream;

public class Parser {

    public static final String NULL = "-";

    public Stream<LogRecord> parse(String string) throws IOException, InterruptedException {
        List<LogRecord> logs = new ArrayList<>();

        try {
            Path of = Path.of(Configuration.FILE_FOR_DOWNLOAD);
            if (!Files.exists(of)) {
                Files.createFile(of);
                URI uri = URI.create(string);
                LogsApi.getNginxLogs(uri, Configuration.FILE_FOR_DOWNLOAD);
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(of.toFile()))) {
                while (reader.ready()) {
                    logs.add(parseLog(reader.readLine()));
                }
                return logs.stream();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IllegalArgumentException e) {
            try {
                Path directory = Path.of(string);
                List<String> files = Files.readAllLines(directory);
                for (int i = 0; i < files.size(); i++) {
                    logs.add(parseLog(files.get(i)));
                }
                return logs.stream();
            } catch (NoSuchFileException exception) {
                throw new IllegalArgumentException("Invalid Path or URI", exception);
            }

        }

    }

    @SuppressWarnings("checkstyle:MagicNumber")
    private LogRecord parseLog(String log) {
        String[] elements = log.split(" ");
        String address = elements[0];
        String user = elements[2].equals("-") ? null : elements[2];
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ssZ", Locale.ENGLISH);
        OffsetDateTime offsetDateTime =
            OffsetDateTime.parse(elements[3].substring(1).concat(elements[4].substring(0, 5)), dateTimeFormatter);
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

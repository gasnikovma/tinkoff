package edu.project3;

import edu.project3.api.LogsApi;
import edu.project3.model.LogRecord;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

public class Parser {

    public static final String FILE_FOR_DOWNLOAD = "src/main/java/edu/project3/download/data.txt";

    public Stream<LogRecord> parse(String uri) throws IOException, InterruptedException {
        Path of = Path.of(FILE_FOR_DOWNLOAD);
        if (!Files.exists(of)) {
            Files.createFile(of);
            LogsApi.getNginxLogs(uri, FILE_FOR_DOWNLOAD);
        }
        List<LogRecord> logs = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(of.toFile()))) {
            while (reader.ready()) {
                logs.add(parseLog(reader.readLine()));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return logs.stream();
    }

    private LogRecord parseLog(String log) {
        String[] elements = log.split(" ");
        String address = elements[0];
        String user = elements[2].equals("-") ? null : elements[2];
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ssZ", Locale.ENGLISH);
        OffsetDateTime offsetDateTime =
            OffsetDateTime.parse(elements[3].substring(1).concat(elements[4].substring(0, 5)), dateTimeFormatter);
        String request = elements[5].concat(elements[6]).substring(1, elements[5].length() + elements[6].length() - 1);
        Integer status = Integer.parseInt(elements[8]);
        Integer bodyBites = Integer.parseInt(elements[9]);
        String httRefer = elements[10].equals("\"-\"") ? null : elements[10];
        StringBuilder userAgentBuilder = new StringBuilder();
        for (int i = 11; i < elements.length-1; i++) {
            userAgentBuilder.append(elements[i]+" ");
        }
        userAgentBuilder.append(elements[elements.length-1]);
        String userAgent = elements[10].equals("\"-\"") ? null : String.valueOf(userAgentBuilder);
        String httpUserAgent = null;
        if (userAgent != null) {
            httpUserAgent = userAgent.substring(1, userAgent.length() - 1);
        }
        return new LogRecord(address, user, offsetDateTime, request, status, bodyBites, httRefer, httpUserAgent);

    }

}

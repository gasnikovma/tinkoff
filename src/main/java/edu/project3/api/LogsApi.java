package edu.project3.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import static java.net.http.HttpClient.newHttpClient;

public class LogsApi {
    private final static int STATUS_OK = 200;

    private LogsApi() {

    }

    public static HttpResponse<Path> getNginxLogs(URI uri, String path) throws IOException, InterruptedException {
        var request = HttpRequest.newBuilder().uri(uri).GET().build();
        var response = newHttpClient().send(request, HttpResponse.BodyHandlers.ofFile(Path.of(path)));
        if (response.statusCode() == STATUS_OK) {
            return response;
        } else {
            return null;
        }
    }
}

package edu.project3.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import static java.net.http.HttpClient.newHttpClient;

public class LogsApi {

    private LogsApi() {

    }

    public static HttpResponse<Path> getNginxLogs(String uri,String path) throws IOException, InterruptedException {
        var request = HttpRequest.newBuilder().uri(URI.create(uri)).GET().build();
        var response = newHttpClient().send(request, HttpResponse.BodyHandlers.ofFile(Path.of(path)));
        if (response.statusCode() == 200) {
            return response;
        } else {
            return null;
        }
    }
}

package edu.hw6;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.net.http.HttpClient.newHttpClient;

public class HackerNews {
    private HackerNews() {

    }

    private static final int STATUSCODE = 200;

    public static long[] hackerNewsTopStories() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request =
            HttpRequest.newBuilder().uri(new URI("https://hacker-news.firebaseio.com/v0/topstories.json")).GET()
                .build();
        HttpResponse<String> response = newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != STATUSCODE) {
            return new long[] {};
        }
        return parser(response.body());
    }

    public static String news(long id) throws URISyntaxException, IOException, InterruptedException {
        String uri = "https://hacker-news.firebaseio.com/v0/item/%d.json".formatted(id);
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(uri)).build();
        var response = newHttpClient().send(httpRequest, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != STATUSCODE) {
            return "";
        }
        String body = response.body();
        Matcher title = Pattern.compile("\"title\":\"([^\"]*)\"").matcher(body);
        if (title.find()) {
            return title.group(1);
        } else {
            return "";
        }
    }

    public static long[] parser(String string) {
        return Arrays.stream(string.substring(1, string.length() - 1).split(",")).mapToLong(Long::parseLong).toArray();
    }
}

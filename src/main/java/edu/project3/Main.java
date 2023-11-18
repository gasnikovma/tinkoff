package edu.project3;

import edu.project3.model.LogRecord;
import java.io.IOException;
import java.util.stream.Stream;

public class Main {
    private Main(){

    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Parser parser = new Parser();
        Stream<LogRecord> got = parser.parse("https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs");
        got.filter(it->it.httpRefer()!=null && it.httpUserAgent()!=null).forEach(System.out::println);
    }
}

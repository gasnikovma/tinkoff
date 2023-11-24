package edu.project3;

import edu.project3.Configuration;
import edu.project3.api.LogsApi;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import static org.junit.jupiter.api.Assertions.*;

class LogsApiTest {

    @Test
    void getNginxLogs() throws IOException, InterruptedException {
        URI uri = URI.create("https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_lgs");
        assertNull(LogsApi.getNginxLogs(uri, Configuration.FILE_FOR_DOWNLOAD));
    }
}

package edu.hw6;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class HackerNewsTest {

    @ParameterizedTest
    @MethodSource("provider")
    void newsTest_shouldReturnTitle(String id, String expected)
        throws URISyntaxException, IOException, InterruptedException {
        assertEquals(expected, HackerNews.news(Integer.parseInt(id)));

    }

    private static Stream<Arguments> provider() {
        return Stream.of(
            Arguments.of("37570037", "JDK 21 Release Notes"),
            Arguments.of("3710", " How the young open-source kids are talking (CARTOON)")

        );
    }
}

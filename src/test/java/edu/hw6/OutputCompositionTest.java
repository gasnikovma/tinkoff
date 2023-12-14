package edu.hw6;

import edu.hw6.task4.OutputComposition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class OutputCompositionTest {

    @Test
    @DisplayName("создание файла с заданным текстом")
    void createComposeStream() throws IOException {
        OutputComposition.createComposeStream();
    }
}

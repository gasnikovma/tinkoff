package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Task2Test {

    @Test
    @DisplayName("проверка копий")
    void cloneFile_ShouldReturnTrue() throws IOException {
        Path filePath = Path.of("../Tinkoff Bank Biggest Secret - копия.txt");
        Task2.cloneFile(filePath);
        System.out.println(filePath);
        Path clone = Path.of("../Tinkoff Bank Biggest Secret - копия(2).txt");
        assertTrue(Files.exists(filePath));
        assertTrue(Files.exists(clone));
    }
}

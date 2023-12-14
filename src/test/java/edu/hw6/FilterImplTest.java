package edu.hw6;

import edu.hw6.task3.FilterImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FilterImplTest {
    public static final Path directory = Path.of("src/main/java/edu/hw6/task3/files");

    @Test
    @DisplayName("проверка на размер файла")
    void FilterImplTest_LessThan() {
        List<Path> get = new ArrayList<>();
        List<Path> expected = List.of(
            Path.of("src/main/java/edu/hw6/task3/files/data.txt")
        );
        DirectoryStream.Filter<Path> filter = FilterImpl.regularFile.and(FilterImpl.lessThan(50));
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(directory, filter)) {
            for (var entry : entries) {
                get.add(entry);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertEquals(get, expected);
    }

    @Test
    @DisplayName("проверка на формат файла")
    void FilterImplTest_GlobMatchesAndMagicNumbers() {
        List<Path> get = new ArrayList<>();
        List<Path> expected = List.of(
            Path.of("src/main/java/edu/hw6/task3/files/java.png")
        );
        DirectoryStream.Filter<Path> filter = FilterImpl.regularFile.and(FilterImpl.globMatches("*.png"))
            .and(FilterImpl.magicNumber(0x89, 'P', 'N', 'G'));
        ;
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(directory, filter)) {
            for (var entry : entries) {
                get.add(entry);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertEquals(get, expected);
    }

}

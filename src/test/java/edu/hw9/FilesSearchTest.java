package edu.hw9;

import edu.hw9.task2.DirectorySizeTask;
import edu.hw9.task2.PredicateSearchTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilesSearchTest {

    @BeforeEach
    void init() {
        try {
            Path files = Path.of("Files");
            if (!Files.exists(files)) {
                Files.createDirectories(files);
                for (int i = 0; i < 1000; i++) {
                    String fileName = "file" + i + ".txt";
                    Path filePath = files.resolve(fileName);
                    Files.write(filePath, "File".getBytes());
                }
                String name = "big.txt";
                Path path = files.resolve(name);
                Files.write(path, "oinfdoiewnfewoinfewoinin".getBytes());
                for (int i = 1000; i < 2000; i++) {
                    String fileName = "file" + i + ".pdf";
                    Path filePath = files.resolve(fileName);
                    Files.write(filePath, "Pdf".getBytes());
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void searchByPredicate() {
        Path files = Path.of("Files");
        PredicateSearchTask predicateExtension =
            new PredicateSearchTask(files, path -> path.toString().endsWith(".txt"));
        PredicateSearchTask predicateSize = new PredicateSearchTask(files, path -> {
            try {
                return Files.size(path) > 10;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        try (ForkJoinPool forkJoinPool = new ForkJoinPool();) {
            List<String> result = forkJoinPool.invoke(predicateExtension);
            assertEquals(result.size(), 1001);
            List<String> res = forkJoinPool.invoke(predicateSize);
            assertTrue(res.size() == 1 && Path.of(res.get(0)).equals(files.resolve("big.txt")));

        }

    }

    @Test
    void searchByDirectorySize() {
        Path files = Path.of("Files");
        DirectorySizeTask directorySizeTask = new DirectorySizeTask(files);
        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            List<Path> result = forkJoinPool.invoke(directorySizeTask);
            assertEquals(result.size(), 1);

        }

    }

}

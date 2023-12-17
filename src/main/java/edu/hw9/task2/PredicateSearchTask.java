package edu.hw9.task2;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.function.Predicate;

public class PredicateSearchTask extends RecursiveTask<List<String>> {
    private final Path directory;
    private final Predicate<Path> predicate;

    public PredicateSearchTask(Path directory, Predicate<Path> predicate) {
        this.directory = directory;
        this.predicate = predicate;
    }

    @Override
    protected List<String> compute() {
        List<String> result = new ArrayList<>();
        if (Files.isDirectory(directory)) {
            List<PredicateSearchTask> predicateSearchTasks = new ArrayList<>();
            try (DirectoryStream<Path> paths = Files.newDirectoryStream(directory)) {
                for (Path path : paths) {
                    if (Files.isRegularFile(path)) {
                        if (predicate.test(path)) {
                            result.add(path.toString());
                        }
                    } else if (Files.isDirectory(path)) {
                        PredicateSearchTask task = new PredicateSearchTask(path, predicate);
                        task.fork();
                        predicateSearchTasks.add(task);
                    }

                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            for (PredicateSearchTask task : predicateSearchTasks) {
                result.addAll(task.join());
            }
        }
        return result;
    }
}

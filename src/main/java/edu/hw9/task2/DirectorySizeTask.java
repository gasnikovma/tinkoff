package edu.hw9.task2;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class DirectorySizeTask extends RecursiveTask<List<Path>> {

    private final Path directory;
    private static final int FILES = 1000;

    public DirectorySizeTask(Path directory) {
        this.directory = directory;
    }

    @Override
    protected List<Path> compute() {
        List<Path> result = new ArrayList<>();
        if (Files.isDirectory(directory)) {
            List<DirectorySizeTask> directorySizeTasks = new ArrayList<>();
            try (DirectoryStream<Path> paths = Files.newDirectoryStream(directory)) {
                int cnt = 0;
                for (Path path : paths) {
                    if (Files.isRegularFile(path)) {
                        cnt++;
                    } else if (Files.isDirectory(path)) {
                        DirectorySizeTask task = new DirectorySizeTask(path);
                        task.fork();
                        directorySizeTasks.add(task);
                    }

                }
                if (cnt > FILES) {
                    result.add(directory);
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            for (DirectorySizeTask subtask : directorySizeTasks) {
                result.addAll(subtask.join());
            }
        }
        return result;
    }
}

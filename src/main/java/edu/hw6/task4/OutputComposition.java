package edu.hw6.task4;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;

public class OutputComposition {
    private OutputComposition() {

    }

    @SuppressWarnings("NestedTryDepth")
    public static void createComposeStream() throws IOException {
        Path path = Path.of("src/main/java/edu/hw6/task4/files/input.txt");
        if (Files.exists(path)) {
            Files.delete(path);
        }
        File file = Files.createFile(path).toFile();
        try (OutputStream outputStream = new FileOutputStream(file)) {
            try (CheckedOutputStream checkedOutputStream = new CheckedOutputStream(outputStream, new Adler32())) {
                try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(checkedOutputStream)) {
                    try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                        bufferedOutputStream,
                        StandardCharsets.UTF_8
                    )) {
                        try (PrintWriter printWriter = new PrintWriter(outputStreamWriter)) {
                            printWriter.write("Programming is learned by writing programs. ― Brian Kernighan");
                        }
                    }

                }
            }
        }
    }
}

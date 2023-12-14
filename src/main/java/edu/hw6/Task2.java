package edu.hw6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Task2 {
    private Task2() {

    }

    public static void cloneFile(Path path) throws IOException {
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
        int counter = 0;
        boolean isCopy = false;
        try {
            String fileName = path.getFileName().toString();
            String fileExtension = "";
            Path newFilePath;
            String name = "";
            int extensionIndex = fileName.lastIndexOf(".");
            if (fileName.contains("копия")) {
                isCopy = true;
                if (Character.isDigit(fileName.charAt(extensionIndex - 2))) {
                    counter = Integer.parseInt(String.valueOf(fileName.charAt(extensionIndex - 2))) + 1;
                    name = fileName.substring(0, fileName.lastIndexOf('я') + 1);
                } else {
                    counter = 2;
                    name = fileName.substring(0, extensionIndex);
                }
            } else {
                name = fileName.substring(0, extensionIndex);
            }
            fileExtension = fileName.substring(extensionIndex);
            newFilePath =
                Paths.get(
                    path.getParent().toString(),
                    name + (!isCopy ? " - копия" : "") + (counter != 0 ? "(" + counter + ")" : "") + fileExtension
                );

            if (!Files.exists(newFilePath)) {

                Files.copy(path, newFilePath);
            }
        } catch (IOException e) {
            throw new IOException();
        }
    }

}

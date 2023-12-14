package edu.hw6.task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DiskMap implements Map<String, String> {
    private Path path;

    public DiskMap() throws IOException {
        createDirectory();
    }

    @Override
    public int size() {
        return entrySet().size();
    }

    private void createDirectory() throws IOException {
        path = Paths.get("src/main/java/edu/hw6/task1").resolve("diskMap");
        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }

    }

    @Override
    public boolean isEmpty() {
        return entrySet().isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return keySet().contains(key);
    }

    @Override
    public boolean containsValue(Object value) {
        for (var curKey : keySet()) {
            if (get(curKey).equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String get(Object key) {
        Path filePath = path.resolve(key.toString());
        String value;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath.toString()))) {
            value = bufferedReader.readLine();
        } catch (FileNotFoundException e) {
            value = null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return value;
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        Path filePath = path.resolve(key);
        String returned = remove(key);
        try {
            Files.createFile(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toString()))) {
            writer.write(value);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return returned;
    }

    @Override
    public String remove(Object key) {
        Path filePath = path.resolve(key.toString());
        String returned = null;
        if (containsKey(key)) {
            returned = get(key);
            try {
                Files.delete(filePath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return returned;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        for (Entry<? extends String, ? extends String> d : m.entrySet()) {
            put(d.getKey(), d.getValue());

        }
    }

    @Override
    public void clear() {
        for (String keys : keySet()) {
            remove(keys);
        }
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        Set<String> set = new HashSet<>();
        File[] keys = new File(String.valueOf(path)).listFiles();
        for (File fileKey : keys) {
            set.add(fileKey.getName());
        }
        return set;
    }

    @NotNull
    @Override
    public Collection<String> values() {
        Collection<String> mapValues = new ArrayList<>();
        for (var curKey : keySet()) {
            mapValues.add(get(curKey));
        }
        return mapValues;
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        Set<Entry<String, String>> set = new HashSet<>();
        for (String fileKey : keySet()) {
            set.add(new AbstractMap.SimpleEntry<>(fileKey, get(fileKey)));
        }
        return set;
    }
}

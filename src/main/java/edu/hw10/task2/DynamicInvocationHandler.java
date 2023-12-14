package edu.hw10.task2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DynamicInvocationHandler implements InvocationHandler {
    private final Object field;
    private final Path directory;
    private final Map<String, Object> cache = new HashMap<>();

    public DynamicInvocationHandler(Object field) throws IOException {
        this.field = field;
        directory = Files.createTempDirectory("fibonacci");
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
        throws IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        if (method.isAnnotationPresent(Cache.class)) {
            String key = generateKey(method, args);
            if (method.getAnnotation(Cache.class).persist()) {
                Path file = Paths.get(directory.toString(), key);
                if (Files.exists(file)) {
                    try (
                        ObjectInputStream objectInputStream = new ObjectInputStream(
                            new FileInputStream(file.toString()))) {
                        return objectInputStream.readObject();
                    }

                } else {
                    try (
                        ObjectOutputStream outputStream = new ObjectOutputStream(
                            new FileOutputStream(file.toString()))) {
                        Object cur = method.invoke(field, args);
                        outputStream.writeObject(cur);
                    } catch (InvocationTargetException e) {
                        throw new RuntimeException(e);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }

            } else {
                if (cache.containsKey(key)) {
                    return method.invoke(field, args);
                }
                return cache.get(key);
            }
        }
        return method.invoke(field, args);
    }

    public String generateKey(Method method, Object[] args) {
        return "random" + " " + Arrays.toString(args);
    }
}

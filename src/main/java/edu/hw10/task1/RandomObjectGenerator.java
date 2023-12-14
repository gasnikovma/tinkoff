package edu.hw10.task1;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.RecordComponent;
import java.util.Arrays;
import java.util.Random;

public class RandomObjectGenerator {

    private final Random random = new Random();

    public <T> T nextObject(Class<T> classz)
        throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (classz.isRecord()) {
            return getRecord(classz);
        }
        try {
            T instance = classz.getDeclaredConstructor().newInstance();
            for (Field field : classz.getDeclaredFields()) {
                if (field.isAnnotationPresent(NotNull.class)) {
                    field.set(instance, generateValForField(field.getType()));
                }
                if (field.isAnnotationPresent(Min.class)) {
                    int minValue = field.getAnnotation(Min.class).value();
                    Object value = generateValForField(field.getType());
                    while (((Comparable) value).compareTo(minValue) < 0) {
                        value = generateValForField(field.getType());
                    }
                    field.set(instance, value);
                }
                if (field.isAnnotationPresent(Max.class)) {
                    int maxValue = field.getAnnotation(Max.class).value();
                    Object value = generateValForField(field.getType());
                    while (((Comparable) value).compareTo(maxValue) > 0) {
                        value = generateValForField(field.getType());
                    }
                    field.set(instance, value);
                }
            }
            return instance;

        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException
                 | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private <T> T getRecord(Class<T> classz)
        throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        RecordComponent[] components = classz.getRecordComponents();
        Class[] types = new Class[classz.getRecordComponents().length];
        Object[] fields = new Object[classz.getRecordComponents().length];
        int index = 0;
        for (var component : components) {
            types[index++] = component.getType();
        }
        Field[] fields1 = classz.getDeclaredFields();
        Constructor<T> constructor = classz.getDeclaredConstructor(types);
        index = 0;
        for (Field field : fields1) {
            if (field.isAnnotationPresent(NotNull.class)) {
                fields[index] = generateValForField(types[index]);
                index += 1;
            } else if (field.isAnnotationPresent(Min.class)) {
                int minValue = field.getAnnotation(Min.class).value();
                Object value = generateValForField(types[index]);
                while (((Comparable) value).compareTo(minValue) < 0) {
                    value = generateValForField(types[index]);
                }
                fields[index] = value;
                index += 1;
            } else if (field.isAnnotationPresent(Max.class)) {
                int maxValue = field.getAnnotation(Max.class).value();
                Object value = generateValForField(types[index]);
                while (((Comparable) value).compareTo(maxValue) > 0) {
                    value = generateValForField(types[index]);
                }
                fields[index] = value;
                index += 1;
            }
        }

        Arrays.stream(fields).forEach(System.out::println);
        return constructor.newInstance(fields);
    }

    public <T> T nextObject(Class<T> classz, String factoryMethod) {
        try {
            Method method = classz.getDeclaredMethod(factoryMethod);
            for (Field field : classz.getDeclaredFields()) {
                if (field.isAnnotationPresent(NotNull.class)) {
                    field.set(method, generateValForField(field.getType()));
                }
                if (field.isAnnotationPresent(Min.class)) {
                    int minValue = field.getAnnotation(Min.class).value();
                    Object value = generateValForField(field.getType());
                    while (((Comparable) value).compareTo(minValue) < 0) {
                        value = generateValForField(field.getType());
                    }
                    field.set(method, value);
                }
                if (field.isAnnotationPresent(Max.class)) {
                    int maxValue = field.getAnnotation(Max.class).value();
                    Object value = generateValForField(field.getType());
                    while (((Comparable) value).compareTo(maxValue) > 0) {
                        value = generateValForField(field.getType());
                    }
                    field.set(method, value);
                }
            }
            return (T) method.invoke(null);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private Object generateValForField(Class<?> fieldType) {
        if (fieldType.equals(int.class) || fieldType.equals(Integer.class)) {
            return random.nextInt();
        } else if (fieldType.equals(double.class) || fieldType.equals(Double.class)) {
            return random.nextDouble();
        } else if (fieldType.equals(String.class)) {
            return "randomString";
        } else {
            return null;
        }

    }
}


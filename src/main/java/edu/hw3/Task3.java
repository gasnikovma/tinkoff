package edu.hw3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task3 {
    private Task3() {

    }

    public static <T> Map<T, Integer> getFrequency(List<T> input) {
        Map<T, Integer> dict = new HashMap<>();
        for (int i = 0; i < input.size(); i++) {
            T element = input.get(i);
            if (dict.containsKey(element)) {
                dict.put(element, dict.get(element) + 1);
            } else {
                dict.put(element, 1);
            }
        }
        return dict;

    }
}

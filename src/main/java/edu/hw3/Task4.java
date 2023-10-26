package edu.hw3;

import java.util.TreeMap;

public class Task4 {
    private Task4() {

    }

    @SuppressWarnings("checkstyle:magicnumber")
    public static String convertToRoman(int value) {
        TreeMap<Integer, String> map = new TreeMap<Integer, String>();
        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");
        int l = map.floorKey(value);
        if (l == value) {
            return map.get(value);
        } else {
            return map.get(l) + convertToRoman(value - l);
        }
    }
}

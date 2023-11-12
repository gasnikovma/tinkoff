package edu.hw5;

import java.util.regex.Pattern;

public class Task5 {
    private Task5() {

    }

    public static final Pattern VALIDATE = Pattern.compile("^[А-Я]\\d{3}[А-Я]{2}\\d{3}$");

    public static boolean isCorrectStateNumber(String number) {
        return VALIDATE.matcher(number).find();

    }
}

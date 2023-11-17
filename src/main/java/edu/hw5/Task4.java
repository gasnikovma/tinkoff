package edu.hw5;

import java.util.regex.Pattern;

public class Task4 {
    public static final Pattern VALIDATE = Pattern.compile("[~!@#$%^&*|]");

    private Task4() {

    }

    public static boolean isSuitablePassword(String password) {
        return VALIDATE.matcher(password).find();
    }

}

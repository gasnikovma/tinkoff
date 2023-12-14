package edu.hw5;

import java.util.regex.Pattern;

public class Task8 {
    private Task8() {

    }

    public static boolean unevenLength(String string) {
        Pattern pattern = Pattern.compile("^[0,1]([0,1]{2})*$");
        return pattern.matcher(string).matches();
    }

    public static boolean startsWithZeroAndUnevenLengthOrStartsWithOneAndEvenLength(String string) {
        Pattern pattern = Pattern.compile("^((0|1[0,1])([0,1]{2})*)$");
        return pattern.matcher(string).matches();
    }

    public static boolean everyOddLetterIsOne(String string) {
        Pattern pattern = Pattern.compile("^(1[0,1])*1?$");
        return pattern.matcher(string).matches();
    }

    public static boolean moreThanOneZeroAndLessThanTwoOne(String string) {
        Pattern pattern = Pattern.compile("^(0{2,})|(10{2,})|(0{2,}1)|(0+10+)$");
        return pattern.matcher(string).matches();
    }

    public static boolean noSequentialOne(String string) {
        Pattern pattern = Pattern.compile("^1?(0+1?)*0*?");
        return pattern.matcher(string).matches();
    }
}

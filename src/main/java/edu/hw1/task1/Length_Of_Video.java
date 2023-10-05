package edu.hw1.task1;

public final class Length_Of_Video {
    public static int length(String s){
        String[]d = s.split(":");
        int minutes = Integer.parseInt(String.valueOf(d[0]));
        int seconds = Integer.parseInt(String.valueOf(d[1]));
        if(seconds>=60){
            return -1;
        }
        return minutes*60+seconds;
    }
}

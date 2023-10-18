package edu.hw2.task4;

public class Utils {
    private Utils() {

    }

    public static CallingInfo callingInfo() {
        Throwable e = new Throwable();
        StackTraceElement[] stktrace
            = e.getStackTrace();
        return new CallingInfo(stktrace[1].getClassName(), stktrace[1].getMethodName());
    }
}

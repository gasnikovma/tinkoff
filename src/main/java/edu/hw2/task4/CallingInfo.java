package edu.hw2.task4;

public record CallingInfo(String className, String methodName) {
    public static CallingInfo callingInfo() {
        Throwable e = new Throwable();
        StackTraceElement[] stktrace
            = e.getStackTrace();
        return new CallingInfo(stktrace[1].getClassName(), stktrace[1].getMethodName());
    }
}

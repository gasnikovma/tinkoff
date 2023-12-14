package edu.hw10.task2;

public interface FibCalc {
    @Cache(persist = true)
    public long fib(int number);
}

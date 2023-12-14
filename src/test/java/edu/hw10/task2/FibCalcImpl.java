package edu.hw10.task2;

public class FibCalcImpl implements FibCalc{
    @Override
    public long fib(int number) {
        if (number == 1 || number == 0) {
            return number;
        }
        return fib(number-1) + fib(number-2);
    }
}

package edu.hw3;

import java.util.Comparator;

public class NullComparator<T> implements Comparator<T> {
    private final Comparator<T> real;

    NullComparator(Comparator<? super T> real) {
        this.real = (Comparator<T>) real;
    }

    @Override
    public int compare(T o1, T o2) {
        if (o1 == null) {
            return o2 == null ? 0 : -1;
        } else if (o2 == null) {
            return 1;
        }
        return (real == null) ? 0 : real.compare(o1, o2);
    }

}

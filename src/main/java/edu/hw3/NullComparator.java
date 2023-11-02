package edu.hw3;

import java.util.Comparator;

public class NullComparator<T> implements Comparator<T> {
    private final Comparator<T> real;

    NullComparator(Comparator<? super T> real) {
        this.real = (Comparator<T>) real;
    }

    @Override
    public int compare(T o1, T o2) {
        if (o1 == o2) {
            return 0;
        }
        if (o2 == null) {
            return -1;
        }
        if (o1 == null) {
            return 1;
        }
        return real.compare(o1, o2);
    }

}

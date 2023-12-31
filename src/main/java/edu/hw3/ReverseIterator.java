package edu.hw3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class ReverseIterator<T> implements Iterator<T> {
    private final List<T> list;
    private int index;

    public ReverseIterator(Collection<T> collection) {
        this.list = new ArrayList<>(collection);
        this.index = collection.size() - 1;
    }

    @Override
    public boolean hasNext() {
        return index >= 0;
    }

    @Override
    public T next() {
        if (index < 0) {
            throw new NoSuchElementException("выход за пределы массива");
        }

        return list.get(index--);
    }
}

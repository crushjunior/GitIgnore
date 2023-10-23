package ru.charushnikov.gitignore.array_list;

import java.util.Iterator;

public class ArrayIterator<E> implements Iterator<E> {
    private int index;
    E[] values;

    public ArrayIterator(E[] values) {
        this.values = values;
    }


    @Override
    public boolean hasNext() {
        return index < values.length;
    }

    @Override
    public E next() {
        return values[index++];
    }

}

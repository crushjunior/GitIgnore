package ru.charushnikov.gitignore.array_list;

import java.util.Collection;
import java.util.Comparator;

public interface ArrListContInterface<E> extends Iterable<E> {
    void add(int index, E element);

    boolean addAll(ArrListContInterface<? extends E> c);

    void clear();

    E get(int index);

    boolean isEmpty();

    void remove(int index);

    void remove(Object o);

    void sort();

    int getSize();
}

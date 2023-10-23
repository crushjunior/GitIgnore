package ru.charushnikov.gitignore.array_list;

import java.util.Comparator;

public class MyComparator <E> implements Comparator<E> {
    @Override
    public int compare(E obj1, E obj2) {
        if (obj1 instanceof Comparable && obj2 instanceof Comparable) {
            Comparable<E> comp1 = (Comparable<E>) obj1;
            return comp1.compareTo(obj2);
        }

        return 0;
    }
}

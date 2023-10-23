package ru.charushnikov.gitignore.array_list;


import java.util.*;

public class ArrayListContainer<E> implements ArrListContInterface<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    private MyComparator<? super E> comparator;


    public ArrayListContainer() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
        comparator = new MyComparator<>();
    }

    public ArrayListContainer(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException();
        }
        elements = new Object[capacity];
        size = 0;
        comparator = new MyComparator<>();
    }


    @Override
    public void add(int index, E element) {
        validateIndex(index);
        ensureCapacity(size + 1);

        if (index == size) {
            elements[index] = element;
        } else {
            System.arraycopy(elements, index, elements, index + 1, size - index);
            elements[index] = element;
        }
        size++;
    }

    @Override
    public boolean addAll(ArrListContInterface<? extends E> c) {
        for (int i = 0; i < c.getSize(); i++) {
            add(size, c.get(i));
        }
        return true;
    }

    @Override
    public void clear() {
        for (int i = 0; i < elements.length - 1; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public E get(int index) {
        validateIndex(index);
        return (E) elements[index];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void remove(int index) {
        validateIndex(index);
        if (index == size - 1) {
            elements[index] = null;
        } else {
            for (int i = index; i < size - 1; i++) {
                elements[i] = elements[i + 1];
            }
            elements[size - 1] = null;
        }
        size--;
    }

    @Override
    public void remove(Object o) {
        if (o != null) {
            for (int i = 0; i < size; i++) {
                if (elements[i].equals(o)) {
                    remove(i);
                    break;
                }
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void sort() {
        quickSort(elements, 0, elements.length - 1);
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayIterator<>((E[]) elements);
    }


    public int getSize() {
        return size;
    }

    private void validateIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void ensureCapacity(int minCapacity) {
        int currentCapacity = elements.length;
        if (minCapacity > currentCapacity) {
            int newCapacity = currentCapacity + currentCapacity / 2;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            elements = Arrays.copyOf(elements, newCapacity);
        }


    }

    private void quickSort(Object[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private int partition(Object[] arr, int begin, int end) {
        Object pivot = arr[end];
        int i = (begin - 1);


        for (int j = begin; j < end; j++) {
            int compare = comparator.compare((E)elements[j], (E)pivot);

            if (compare == -1) {
                i++;

                swapElements(arr, i, j);
            }
        }

        swapElements(arr, i + 1, end);
        return i + 1;
    }

    private static void swapElements(Object[] arr, int left, int right) {
        Object temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

}

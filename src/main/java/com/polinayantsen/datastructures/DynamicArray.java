package com.polinayantsen.datastructures;

/**
 * A generic dynamic array implementation
 */
@SuppressWarnings("unchecked")
public class DynamicArray<T> implements Iterable<T> {

    private T[] array;
    private int length = 0; // length user thinks array is
    private int capacity = 0; // actual array size

    public DynamicArray() {
        this(16);
    }

    public DynamicArray(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Illegal capacity: " + capacity);
        }
        this.capacity = capacity;
        array = (T[]) new Object[capacity];
    }

    public int size() {
        return length;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public T get(int index) {
        if (index >= length || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        return array[index];
    }

    public void set(int index, T element) {
        if (index >= length || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        array[index] = element;
    }

    public void clear() {
        for (int i = 0; i < length; i++) {
            array[i] = null;
        }
        length = 0;
    }

    public void add(T element) {
        if (length + 1 >= capacity) { // Time to resize!
            if (capacity == 0) {
                capacity = 1;
            } else {
                capacity *= 2; // double the size
            }
            T[] new_array = (T[]) new Object[capacity];
            for (int i = 0; i < length; i++) { // TODO: consider using arraycopy
                new_array[i] = array[i];
            }
            array = new_array;
        }
        array[length++] = element;
    }

    // Removes an element at the specified index in this array.
    public T removeAt(int removeIndex) {
        if (removeIndex >= length || removeIndex < 0) {
            throw new IndexOutOfBoundsException();
        }
        T data = array[removeIndex];
        T[] new_array = (T[]) new Object[length - 1];
        for (int i = 0, j = 0; i < length; i++, j++) { // TODO: consider using arraycopy
            if (i == removeIndex) {
                j--; // Skip over removeIndex by fixing j temporarily
            } else {
                new_array[j] = array[i];
            }
        }
        array = new_array;
        capacity = --length;
        return data;
    }

    public boolean remove(Object obj) {
        int index = indexOf(obj);
        if (index == -1) {
            return false;
        }
        removeAt(index);
        return true;
    }

    public int indexOf(Object obj) {
        for (int i = 0; i < length; i++) {
            if (obj == null) {
                if (array[i] == null) {
                    return i;
                }
            } else {
                if (obj.equals(array[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    // Iterator is still fast but not as fast as iterative for loop
    @Override
    public java.util.Iterator<T> iterator() {
        return new java.util.Iterator<T>() { // TODO: check for ConcurrentModificationException

            int index = 0;

            @Override
            public boolean hasNext() {
                return index < length;
            }

            @Override
            public T next() {
                return array[index++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public String toString() {
        if (length == 0) {
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder(length).append("[");
            for (int i = 0; i < length - 1; i++) {
                sb.append(array[i]).append(", ");
            }
            return sb.append(array[length - 1]).append("]").toString();
        }
    }
}

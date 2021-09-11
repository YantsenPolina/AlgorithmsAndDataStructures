package com.polinayantsen.datastructures;

import static java.util.Arrays.copyOf;

/**
 * An implementation of an integer only array
 * which can outperform Java's ArrayList by about a factor of 10-15x.
 */
public class IntArray implements Iterable<Integer> {

    private static final int DEFAULT_CAPACITY = 8;

    public int[] array;
    public int length = 0;
    private int capacity = 0;

    public IntArray() {
        this(DEFAULT_CAPACITY);
    }

    public IntArray(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Illegal capacity: " + capacity);
        }
        this.capacity = capacity;
        array = new int[capacity];
    }

    public IntArray(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        this.array = copyOf(array, array.length);
        capacity = length = array.length;
    }

    public int size() {
        return length;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    // To get/set values without method call overhead you can do
    // array_obj.array[index] instead, you can gain about 10x the speed.
    public int get(int index) {
        return array[index];
    }

    public void set(int index, int element) {
        array[index] = element;
    }

    public void add(int element) {
        if (length + 1 >= capacity) {
            if (capacity == 0) {
                capacity = 1;
            } else {
                capacity *= 2; // double the size
            }
            array = copyOf(array, capacity);
        }
        array[length++] = element;
    }

    // If possible, avoid calling this method as it take O(n) time
    // to remove an element (since you have to reconstruct the array)
    public void removeAt(int removeIndex) {
        System.arraycopy(array, removeIndex + 1, array, removeIndex, length - removeIndex - 1);
        --length;
        --capacity;
    }

    // If possible, avoid calling this method as it take O(n) time
    public boolean remove(int element) {
        for (int i = 0; i < length; i++) {
            if (array[i] == element) {
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    public void reverse() {
        for (int i = 0; i < length / 2; i++) {
            int temp = array[i];
            array[i] = array[length - i - 1];
            array[length - i - 1] = temp;
        }
    }

    // Perform a binary search on this array to find an element in O(log(n)) time
    // Make sure this array is sorted. Returns a value < 0 if item is not found
    public int binarySearch(int key) {
        return java.util.Arrays.binarySearch(array, 0, length, key);
    }

    public void sort() {
        java.util.Arrays.sort(array, 0, length);
    }

    @Override
    public java.util.Iterator<Integer> iterator() {
        return new java.util.Iterator<Integer>() {

            int index = 0;

            public boolean hasNext() {
                return index < length;
            }

            public Integer next() {
                return array[index++];
            }

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

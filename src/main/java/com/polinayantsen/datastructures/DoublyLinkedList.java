package com.polinayantsen.datastructures;

/**
 * A doubly linked list implementation.
 */
public class DoublyLinkedList<T> implements Iterable<T> {
    private int size = 0;
    private Node<T> head = null;
    private Node<T> tail = null;

    private static class Node<T> { // Internal node class to represent data
        private T data;
        private Node<T> previous, next;

        public Node(T data, Node<T> previous, Node<T> next) {
            this.data = data;
            this.previous = previous;
            this.next = next;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    public void clear() { // Empty this linked list, O(n)
        Node<T> traverse = head;
        while (traverse != null) {
            Node<T> next = traverse.next;
            traverse.previous = traverse.next = null;
            traverse.data = null;
            traverse = next;
        }
        head = tail = traverse = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void add(T element) {  // Add an element to the tail of the linked list, O(1)
        addLast(element);
    }

    public void addLast(T element) { // Add a node to the tail of the linked list, O(1)
        if (isEmpty()) {
            head = tail = new Node<T>(element, null, null);
        } else {
            tail.next = new Node<T>(element, tail, null);
            tail = tail.next;
        }
        size++;
    }

    public void addFirst(T element) { // Add an element to the beginning of the linked list, O(1)
        if (isEmpty()) {
            head = tail = new Node<T>(element, null, null);
        } else {
            head.previous = new Node<T>(element, null, head);
            head = head.previous;
        }
        size++;
    }

    public void addAt(int index, T data) throws Exception {
        if (index < 0) {
            throw new Exception("Illegal Index");
        }
        if (index == 0) {
            addFirst(data);
            return;
        }
        if (index == size) {
            addLast(data);
            return;
        }
        Node<T> temp = head;
        for (int i = 0; i < index - 1; i++) {
            temp = temp.next;
        }
        Node<T> newNode = new Node<>(data, temp, temp.next);
        temp.next.previous = newNode;
        temp.next = newNode;
        size++;
    }

    public T peekFirst() {  // Check the value of the first node if it exists, O(1)
        if (isEmpty()) {
            throw new RuntimeException("Empty list");
        }
        return head.data;
    }

    public T peekLast() { // Check the value of the last node if it exists, O(1)
        if (isEmpty()) {
            throw new RuntimeException("Empty list");
        }
        return tail.data;
    }

    public T removeFirst() { // Remove the first value at the head of the linked list, O(1)
        if (isEmpty()) {
            throw new RuntimeException("Empty list");
        }
        T data = head.data;
        head = head.next;
        --size;
        if (isEmpty()) {
            tail = null;
        } else {
            head.previous = null;
        }
        return data;
    }

    public T removeLast() { // Remove the last value at the tail of the linked list, O(1)
        if (isEmpty()) {
            throw new RuntimeException("Empty list");
        }
        T data = tail.data;
        tail = tail.previous;
        --size;
        if (isEmpty()) {
            head = null;
        } else {
            tail.next = null;
        }
        return data;
    }

    private T remove(Node<T> node) { // Remove an arbitrary node from the linked list, O(1)
        if (node.previous == null) {
            return removeFirst();
        }
        if (node.next == null) {
            return removeLast();
        }
        node.next.previous = node.previous;
        node.previous.next = node.next;
        T data = node.data;
        node.data = null;
        node = node.previous = node.next = null;
        --size;
        return data;
    }

    public T removeAt(int index) { // Remove a node at a particular index, O(n)
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException();
        }
        int i;
        Node<T> traverse;
        if (index < size / 2) {
            for (i = 0, traverse = head; i != index; i++) {
                traverse = traverse.next;
            }
        } else {
            for (i = size - 1, traverse = tail; i != index; i--) {
                traverse = traverse.previous;
            }
        }
        return remove(traverse);
    }

    public boolean remove(Object object) { // Remove a particular value in the linked list, O(n)
        Node<T> traverse = head;
        if (object == null) {
            for (traverse = head; traverse != null; traverse = traverse.next) {
                if (traverse.data == null) {
                    remove(traverse);
                    return true;
                }
            }
        } else {
            for (traverse = head; traverse != null; traverse = traverse.next) {
                if (object.equals(traverse.data)) {
                    remove(traverse);
                    return true;
                }
            }
        }
        return false;
    }

    public int indexOf(Object object) {  // Find the index of a particular value in the linked list, O(n)
        int index = 0;
        Node<T> traverse = head;
        if (object == null) {
            for (; traverse != null; traverse = traverse.next, index++) {
                if (traverse.data == null) {
                    return index;
                }
            }
        } else {
            for (; traverse != null; traverse = traverse.next, index++) {
                if (object.equals(traverse.data)) {
                    return index;
                }
            }
        }
        return -1;
    }

    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override
    public java.util.Iterator<T> iterator() {
        return new java.util.Iterator<T>() { // TODO: check for ConcurrentModificationException

            private Node<T> traverse = head;

            @Override
            public boolean hasNext() {
                return traverse != null;
            }

            @Override
            public T next() {
                T data = traverse.data;
                traverse = traverse.next;
                return data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        Node<T> traverse = head;
        while (traverse != null) {
            sb.append(traverse.data);
            if (traverse.next != null) {
                sb.append(", ");
            }
            traverse = traverse.next;
        }
        sb.append(" ]");
        return sb.toString();
    }
}

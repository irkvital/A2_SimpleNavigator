package edu.school21.dataStructures;

public class Queue<T> {
    Node<T> top;
    Node<T> bottom;
    int size;

    public Queue() {
        queue();
    }

    public void queue() {
        top = null;
        bottom = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public void push(T value) {
        if (top == null) {
            pushFirst(value);
        } else {
            pushNext(value);
        }
        size++;
    }

    public T pop() {
        T returnedData = null;
        if (bottom == top) {
            returnedData = popLast();
        } else {
            returnedData = popNotLast();
        }

        return returnedData;
    }

    public T front() {
        T returnedData = null;
        if (top != null) {
            returnedData = top.getData();
        }

        return returnedData;
    }

    public T back() {
        T returnedData = null;
        if (bottom != null) {
            returnedData = bottom.getData();
        }

        return returnedData;
    }

    private T popNotLast() {
        T returnedData = bottom.getData();
        bottom = bottom.getNext();
        if (bottom != null) {
            bottom.setPrev(null);
        }
        size--;

        return returnedData;
    }

    private T popLast() {
        T returnedData = bottom.getData();
        bottom = null;
        top = null;
        size--;

        return returnedData;
    }

    private void pushFirst(T value) {
        Node<T> newNode = new Node<>(value);
        top = newNode;
        bottom = newNode;

    }

    private void pushNext(T value) {
        Node<T> newNode = new Node<>(value);
        top.setNext(newNode);
        newNode.setPrev(top);
        top = newNode;
    }
}

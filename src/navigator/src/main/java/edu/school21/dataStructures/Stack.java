package edu.school21.dataStructures;

public class Stack<T> {
    Node<T> top;
    int size;

    public Stack() {
        stack();
    }

    public void stack() {
        top = null;
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

    public boolean isEmpty() {
        return top == null;
    }

    public T pop() {
        T returnedData = null;
        if (top != null) {
            returnedData = top.getData();
            top = top.getPrev();
            if (top != null) {
                top.setNext(null);
            }
            size--;
        }

        return returnedData;
    }

    public T top() {
        T returnedData = null;
        if (top != null) {
            returnedData = top.getData();
        }

        return returnedData;
    }

    private void pushFirst(T value) {
        Node<T> newNode = new Node<>(value);
        top = newNode;

    }

    private void pushNext(T value) {
        Node<T> newNode = new Node<>(value);
        top.setNext(newNode);
        newNode.setPrev(top);
        top = newNode;
    }
}

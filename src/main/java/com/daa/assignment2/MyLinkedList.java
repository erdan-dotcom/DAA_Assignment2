package com.daa.assignment2;

public class MyLinkedList {
    private static class Node {
        int data;
        Node next;
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private int size;
    public final Metrics metrics = new Metrics();

    public MyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private Node getNode(int index) {
        Node current = head;
        for (int i = 0; i < index; i++) {
            metrics.steps++;
            current = current.next;
        }
        metrics.steps++;
        return current;
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return getNode(index).data;
    }

    public void add(int element) {
        Node newNode = new Node(element);
        metrics.moves++;
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                metrics.steps++;
                current = current.next;
            }
            metrics.steps++;
            current.next = newNode;
        }
        size++;
    }

    public void add(int index, int element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node newNode = new Node(element);
        metrics.moves++;
        if (index == 0) {
            newNode.next = head;
            metrics.moves++;
            head = newNode;
        } else {
            Node prev = getNode(index - 1);
            newNode.next = prev.next;
            metrics.moves++;
            prev.next = newNode;
            metrics.moves++;
        }
        size++;
    }

    public int remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        int removedData;
        if (index == 0) {
            metrics.steps++;
            removedData = head.data;
            head = head.next;
            metrics.moves++;
        } else {
            Node prev = getNode(index - 1);
            metrics.steps++;
            removedData = prev.next.data;
            prev.next = prev.next.next;
            metrics.moves++;
        }
        size--;
        return removedData;
    }

    public boolean contains(int element) {
        Node current = head;
        while (current != null) {
            metrics.steps++;
            metrics.comparisons++;
            if (current.data == element) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
}
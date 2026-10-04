package com.daa.assignment2;

public class DynamicArray {
    private int[] data;
    private int size;
    public final Metrics metrics = new Metrics();

    public DynamicArray(int capacity) {
        if (capacity < 0) capacity = 10;
        this.data = new int[capacity];
        this.size = 0;
    }

    public DynamicArray() {
        this(10);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int get(int index) {
        metrics.steps++;
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        metrics.steps++;
        return data[index];
    }

    public void add(int element) {
        if (size >= data.length) {
            resize();
        }
        metrics.moves++;
        data[size++] = element;
    }

    public void add(int index, int element) {
        metrics.steps++;
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (size >= data.length) {
            resize();
        }
        for (int i = size; i > index; i--) {
            metrics.steps++;
            metrics.moves++;
            data[i] = data[i - 1];
        }
        metrics.moves++;
        data[index] = element;
        size++;
    }

    public int remove(int index) {
        metrics.steps++;
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        metrics.steps++;
        int removed = data[index];
        for (int i = index; i < size - 1; i++) {
            metrics.steps++;
            metrics.moves++;
            data[i] = data[i + 1];
        }
        size--;
        return removed;
    }

    public boolean contains(int element) {
        for (int i = 0; i < size; i++) {
            metrics.steps++;
            metrics.comparisons++;
            if (data[i] == element) {
                return true;
            }
        }
        return false;
    }

    private void resize() {
        int newCapacity = data.length == 0 ? 10 : data.length * 2;
        int[] newData = new int[newCapacity];
        for (int i = 0; i < size; i++) {
            metrics.steps++;
            metrics.moves++;
            newData[i] = data[i];
        }
        data = newData;
    }
}
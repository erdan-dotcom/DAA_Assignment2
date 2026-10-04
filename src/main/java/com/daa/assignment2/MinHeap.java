package com.daa.assignment2;

public class MinHeap {
    private int[] heap;
    private int size;
    public final Metrics metrics = new Metrics();

    public MinHeap(int capacity) {
        if (capacity < 1) capacity = 10;
        this.heap = new int[capacity];
        this.size = 0;
    }

    public MinHeap() {
        this(10);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int peekMin() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        metrics.steps++;
        return heap[0];
    }

    public void insert(int element) {
        if (size >= heap.length) {
            resize();
        }
        heap[size] = element;
        metrics.moves++;
        bubbleUp(size);
        size++;
    }

    public int extractMin() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        metrics.steps++;
        int min = heap[0];
        metrics.steps++;
        heap[0] = heap[size - 1];
        metrics.moves++;
        size--;
        if (size > 0) {
            bubbleDown(0);
        }
        return min;
    }

    private void bubbleUp(int index) {
        int currentIndex = index;
        while (currentIndex > 0) {
            metrics.steps++;
            int parentIndex = (currentIndex - 1) / 2;
            metrics.comparisons++;
            if (heap[currentIndex] < heap[parentIndex]) {
                swap(currentIndex, parentIndex);
                currentIndex = parentIndex;
            } else {
                break;
            }
        }
    }

    private void bubbleDown(int index) {
        int currentIndex = index;
        while (2 * currentIndex + 1 < size) {
            metrics.steps++;
            int leftChild = 2 * currentIndex + 1;
            int rightChild = leftChild + 1;
            int smallest = currentIndex;

            metrics.comparisons++;
            if (heap[leftChild] < heap[smallest]) {
                smallest = leftChild;
            }

            if (rightChild < size) {
                metrics.comparisons++;
                if (heap[rightChild] < heap[smallest]) {
                    smallest = rightChild;
                }
            }

            if (smallest != currentIndex) {
                swap(currentIndex, smallest);
                currentIndex = smallest;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        metrics.steps += 2;
        metrics.moves += 3;
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    private void resize() {
        int[] newHeap = new int[heap.length * 2];
        for (int i = 0; i < size; i++) {
            metrics.steps++;
            metrics.moves++;
            newHeap[i] = heap[i];
        }
        heap = newHeap;
    }
}
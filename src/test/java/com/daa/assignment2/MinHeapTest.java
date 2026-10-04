package com.daa.assignment2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MinHeapTest {
    @Test
    public void testHeapPropertyAndExtraction() {
        MinHeap heap = new MinHeap();
        int[] values = {5, 3, 8, 1, 2, 9};
        for (int v : values) {
            heap.insert(v);
        }

        assertEquals(6, heap.size());
        assertEquals(1, heap.peekMin());

        int prev = Integer.MIN_VALUE;
        while (!heap.isEmpty()) {
            int current = heap.extractMin();
            assertTrue(current >= prev);
            prev = current;
        }
    }

    @Test
    public void testExceptions() {
        MinHeap heap = new MinHeap();
        assertThrows(IllegalStateException.class, heap::peekMin);
        assertThrows(IllegalStateException.class, heap::extractMin);
    }
}
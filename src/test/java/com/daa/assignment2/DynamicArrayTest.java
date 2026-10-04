package com.daa.assignment2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DynamicArrayTest {
    @Test
    public void testAddAndGet() {
        DynamicArray arr = new DynamicArray();
        arr.add(10);
        arr.add(20);
        assertEquals(10, arr.get(0));
        assertEquals(20, arr.get(1));
        assertEquals(2, arr.size());
    }

    @Test
    public void testInsertAndRemove() {
        DynamicArray arr = new DynamicArray();
        arr.add(1);
        arr.add(3);
        arr.add(1, 2);
        assertEquals(1, arr.get(0));
        assertEquals(2, arr.get(1));
        assertEquals(3, arr.get(2));

        int removed = arr.remove(1);
        assertEquals(2, removed);
        assertEquals(2, arr.size());
        assertEquals(3, arr.get(1));
    }

    @Test
    public void testContains() {
        DynamicArray arr = new DynamicArray();
        arr.add(5);
        arr.add(10);
        assertTrue(arr.contains(5));
        assertTrue(arr.contains(10));
        assertFalse(arr.contains(99));
    }

    @Test
    public void testExceptions() {
        DynamicArray arr = new DynamicArray();
        assertThrows(IndexOutOfBoundsException.class, () -> arr.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> arr.remove(0));
        assertThrows(IndexOutOfBoundsException.class, () -> arr.add(1, 10));
    }
}
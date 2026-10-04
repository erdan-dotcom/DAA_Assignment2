package com.daa.assignment2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MyLinkedListTest {
    @Test
    public void testAddAndGet() {
        MyLinkedList list = new MyLinkedList();
        list.add(10);
        list.add(20);
        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
        assertEquals(2, list.size());
    }

    @Test
    public void testInsertAndRemove() {
        MyLinkedList list = new MyLinkedList();
        list.add(1);
        list.add(3);
        list.add(1, 2);
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));

        int removed = list.remove(0);
        assertEquals(1, removed);
        assertEquals(2, list.size());
        assertEquals(2, list.get(0));
    }

    @Test
    public void testContains() {
        MyLinkedList list = new MyLinkedList();
        list.add(7);
        list.add(14);
        assertTrue(list.contains(7));
        assertTrue(list.contains(14));
        assertFalse(list.contains(42));
    }

    @Test
    public void testExceptions() {
        MyLinkedList list = new MyLinkedList();
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(1, 10));
    }
}
package com.github.pvkr.algs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LLRBPriorityQueueTest {

    @Test
    void shouldOfferElement() {
        LLRBPriorityQueue<Integer> queue = new LLRBPriorityQueue<>(Integer::compareTo);
        queue.offer(2);
        assertEquals(1, queue.size());
    }

    @Test
    void shouldPeekElement() {
        LLRBPriorityQueue<Integer> queue = new LLRBPriorityQueue<>(Integer::compareTo);
        queue.offer(2);

        Integer element = queue.peek();
        assertEquals(2, element);
        assertEquals(1, queue.size());
    }

    @Test
    void shouldPollElement() {
        LLRBPriorityQueue<Integer> queue = new LLRBPriorityQueue<>(Integer::compareTo);
        queue.offer(2);

        Integer element = queue.poll();
        assertEquals(2, element);
        assertEquals(0, queue.size());
    }

    @Test
    void shouldIterateQueue() {
        LLRBPriorityQueue<Integer> queue = new LLRBPriorityQueue<>(Integer::compareTo);
        queue.offer(2);
        queue.offer(1);
        queue.offer(5);
        queue.offer(4);
        queue.offer(3);

        int[] elements = new int[5];
        int index = 0;
        for (Integer element : queue) {
            elements[index++] = element;
        }

        assertArrayEquals(new int[] {1, 2, 3, 4, 5}, elements);
    }
}
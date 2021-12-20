package com.github.pvkr.algs;

import java.util.AbstractQueue;
import java.util.Arrays;
import java.util.Iterator;

public class BinaryHeap<T extends Comparable<T>> extends AbstractQueue<T> {
    private Object[] a;
    private int n;

    public BinaryHeap() {
        this(1);
    }

    public BinaryHeap(int capacity) {
        this.a = new Object[capacity];
        this.n = 0;
    }

    private BinaryHeap(Object[] a, int n) {
        this.a = Arrays.copyOf(a, a.length);
        this.n = n;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private BinaryHeap<T> binaryHeap = new BinaryHeap<>(a, n);
            @Override
            public boolean hasNext() {
                return binaryHeap.peek() != null;
            }

            @Override
            public T next() {
                return binaryHeap.poll();
            }
        };
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public boolean offer(T t) {
        if (n == a.length) resize();
        a[n++] = t;
        bubbleUp(n - 1);
        return true;
    }

    @Override
    public T poll() {
        if (n == 0) return null;

        Object x = a[0];
        a[0] = a[--n];
        trickleDown(0);

        if (a.length >= 3 * n && a.length >= 2) resize();
        return (T) x;
    }

    @Override
    public T peek() {
        return n >= 1 ? (T) a[0] : null;
    }

    private void bubbleUp(int child) {
        for (int parent = parent(child); parent >= 0 && compare(parent, child) > 0; child = parent, parent = parent(parent))
            swap(parent, child);
    }

    private void trickleDown(int parent) {
        while (true) {
            int left = left(parent);
            int right = right(parent);
            int next;
            if (right < n) {
                next = compare(left, right) < 0 ? left : right;
            } else if (left < n) {
                next = left;
            } else {
                return;
            }

            if (compare(next, parent) < 0) {
                swap(parent, next);
                parent = next;
                continue;
            }

            break;
        }
    }

    private void resize() {
        int desiredCapacity = a.length == n ? a.length * 2 : a.length / 2;
        a = Arrays.copyOf(a, desiredCapacity);
    }

    private int compare(int i, int j) {
        return ((T) a[i]).compareTo((T) a[j]);
    }

    private void swap(int i, int j) {
        Object x = a[i];
        a[i] = a[j];
        a[j] = x;
    }

    private static int left(int i) {
        return 2 * i + 1;
    }

    private static int right(int i) {
        return 2 * i + 2;
    }

    private static int parent(int i) {
        return (i - 1) / 2;
    }
}

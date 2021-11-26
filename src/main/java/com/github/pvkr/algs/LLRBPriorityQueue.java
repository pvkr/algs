package com.github.pvkr.algs;

import java.util.AbstractQueue;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Implementation is based on Left-learning red-black BST (equivalent to 2-3 Tree):
 * 1) No node has two red links connected to it
 * 2) Every path from root to 'null' link has the same number of black links
 * 3) Red links lean left
 */
public class LLRBPriorityQueue<T> extends AbstractQueue<T> {
    private static final boolean RED   = true;
    private static final boolean BLACK = false;

    private static class Node<T> {
        private T value;
        private Node<T> left, right;
        private boolean color; // color of link to parent

        public Node(T value, boolean color) {
            this.value = value;
            this.color = color;
        }
    }

    private static class DeleteContext<R, V> {
        private R ref;
        private V value;
        public DeleteContext(R ref, V value) {
            this.ref = ref;
            this.value = value;
        }
    }

    // Copies structure and poll all elements
    private static class LLRBPriorityQueueIterator<T>  implements Iterator<T> {
        private final LLRBPriorityQueue<T> queue;

        public LLRBPriorityQueueIterator(LLRBPriorityQueue<T> queue) {
            this.queue = new LLRBPriorityQueue<>(queue.comparator);
            this.queue.root = copy(queue.root);
            this.queue.size = queue.size;
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public T next() {
            return queue.poll();
        }

        private static <T> Node<T> copy(Node<T> node) {
            Node<T> copy = new Node<>(node.value, node.color);
            if (node.left != null ) copy.left = copy(node.left);
            if (node.right != null ) copy.right = copy(node.right);
            return copy;
        }
    }

    private Node<T> root;
    private final Comparator<T> comparator;
    private int size;

    public LLRBPriorityQueue(Comparator<T> comparator) {
        this.comparator = comparator;
    }


    @Override
    public boolean offer(T value) {
        if (value == null)
            throw new NullPointerException("argument to offer() is null");

        root = put(root, value);
        root.color = BLACK;
        size++;
        return true;
    }

    @Override
    public T poll() {
        if (isEmpty())
            return null;

        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        DeleteContext<Node<T>,T> context = new DeleteContext<>(root, null);
        deleteMin(context);
        root = context.ref;

        size--;
        if (!isEmpty()) root.color = BLACK;

        return context.value;
    }

    @Override
    public T peek() {
        return getMin(root);
    }

    @Override
    public Iterator<T> iterator() {
        return new LLRBPriorityQueueIterator<>(this);
    }

    @Override
    public int size() {
        return size;
    }

    private Node<T> put(Node<T> a, T value) {
        if (a == null) return new Node<>(value, RED);

        // difference with LLRB Tree in equals case:
        //  we don't override value, instead we add new one to the right
        int cmp = comparator.compare(value, a.value);
        if (cmp < 0)
            a.left = put(a.left,  value);
        else
            a.right = put(a.right, value);

        // fix-up any right-leaning links
        if (isRed(a.right) && !isRed(a.left))      a = rotateLeft(a);
        if (isRed(a.left)  &&  isRed(a.left.left)) a = rotateRight(a);
        if (isRed(a.left)  &&  isRed(a.right))     flipColors(a);

        return a;
    }

    private T getMin(Node<T> a) {
        if (a == null) return null;
        while (a.left != null) {
            a = a.left;
        }

        return a.value;
    }

    private void deleteMin(DeleteContext<Node<T>,T> context) {
        Node<T> a = context.ref;

        if (a.left == null) {
            context.ref = null;
            context.value = a.value;
            return;
        }

        if (!isRed(a.left) && !isRed(a.left.left))
            a = moveRedLeft(a);

        context.ref = a.left;
        deleteMin(context);
        a.left = context.ref;

        context.ref = balance(a);
    }

    /** LLRB Tree basic operations */
    private Node<T> rotateLeft(Node<T> a) {
        assert (a != null) && isRed(a.right);
        Node<T> b = a.right;
        a.right = b.left;
        b.left = a;
        b.color = b.left.color;
        b.left.color = RED;
        return b;
    }

    private Node<T> rotateRight(Node<T> a) {
        assert (a != null) && isRed(a.left);
        Node<T> b = a.left;
        a.left = b.right;
        b.right = a;
        b.color = b.right.color;
        b.right.color = RED;
        return b;
    }

    private void flipColors(Node<T> node) {
        node.color = !node.color;
        node.left.color = !node.left.color;
        node.right.color = !node.right.color;
    }

    private Node<T> moveRedLeft(Node<T> a) {
        assert isRed(a) && !isRed(a.left) && !isRed(a.left.left);
        flipColors(a);
        if (isRed(a.right.left)) {
            a.right = rotateRight(a.right);
            a = rotateLeft(a);
            flipColors(a);
        }
        return a;
    }

    private Node<T> balance(Node<T> a) {
        if (isRed(a.right) && !isRed(a.left)) a = rotateLeft(a);
        if (isRed(a.left) && isRed(a.left.left)) a = rotateRight(a);
        if (isRed(a.left) && isRed(a.right)) flipColors(a);

        return a;
    }

    /** Utility methods */
    private boolean isRed(Node<T> a) {
        if (a == null) return false;
        return a.color == RED;
    }
}

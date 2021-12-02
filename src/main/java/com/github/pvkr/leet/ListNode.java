package com.github.pvkr.leet;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static int[] toArray(ListNode listNode) {
        int size = 0;
        for (ListNode iterator = listNode; iterator != null; iterator = iterator.next) size++;

        int[] array = new int[size];
        ListNode iterator = listNode;
        for (int i = 0; i < size; i++) {
            array[i] = iterator.val;
            iterator = iterator.next;
        }
        return array;
    }

    public static ListNode fromArray(int[] array) {
        ListNode listNode = null;
        for (int i = array.length - 1; i >= 0; i--) {
            listNode = new ListNode(array[i], listNode);
        }
        return listNode;
    }
}
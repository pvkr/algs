package com.github.pvkr.leet;

/**
 * Constraints:
 *  k == lists.length
 *  0 <= k <= 10^4
 *  0 <= lists[i].length <= 500
 *  -10^4 <= lists[i][j] <= 10^4
 *  lists[i] is sorted in ascending order.
 *  The sum of lists[i].length won't exceed 10^4.
 */
public class Task23 {
    public ListNode mergeKLists(ListNode[] lists) {
        return mergeKLists(lists, 0, lists.length);
    }

    private ListNode mergeKLists(ListNode[] lists, int beginInclusive, int endExclusive) {
        if (endExclusive - beginInclusive == 0) return null;
        if (endExclusive - beginInclusive == 1) return lists[beginInclusive];

        int middle = (endExclusive + beginInclusive) / 2;
        return merge2Lists(mergeKLists(lists, beginInclusive, middle), mergeKLists(lists, middle, endExclusive));
    }

    private ListNode merge2Lists(ListNode left, ListNode right) {
        if (left == null) return right;
        if (right == null) return left;

        ListNode merged = null;
        ListNode current = null;
        while (left != null && right != null) {
            ListNode nextNode = null;
            if (left.val - right.val < 0) {
                nextNode = left;
                left = left.next;
            } else {
                nextNode = right;
                right = right.next;
            }

            if (current == null) {
                current = nextNode;
                merged = current;
            } else {
                current.next = nextNode;
                current = current.next;
            }
        }

        if (left == null) current.next = right;
        if (right == null) current.next = left;

        return merged;
    }
}

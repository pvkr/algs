package com.github.pvkr.leet;

import java.util.PriorityQueue;
import java.util.Queue;

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
        Queue<ListNode> parents = new PriorityQueue<>(lists.length + 1, (x, y) -> x.val - y.val);

        for (ListNode list : lists) {
            if (list != null) {
                parents.add(list);
            }
        }

        ListNode merged = null;
        ListNode current = null;
        while (!parents.isEmpty()) {
            ListNode parent = parents.remove();

            ListNode nextNode = new ListNode(parent.val, null);
            if (merged == null) {
                merged = nextNode;
            } else {
                current.next = nextNode;
            }
            current = nextNode;

            if (parent.next != null) {
                parents.add(parent.next);
            }
        }
        return merged;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

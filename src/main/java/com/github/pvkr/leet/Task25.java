package com.github.pvkr.leet;

public class Task25 {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode nextKGroup = head;
        for (int i = 0; i < k - 1 && nextKGroup != null; i++, nextKGroup = nextKGroup.next);
        if (nextKGroup == null) return head;

        ListNode forward = head;
        ListNode backward = reverseKGroup(nextKGroup.next, k);
        for (int i = 0; i < k; i++) {
            ListNode current = forward;
            forward = forward.next;

            current.next = backward;
            backward = current;
        }

        return backward;
    }
}

package com.github.pvkr.leet;

public class Task4 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int l1 = nums1.length;
        int l2 = nums2.length;

        if (((l1 + l2) & 1) == 0) {
            int leftMedian = findElementAt(nums1, 0, nums2, 0, (l1 + l2 - 1) / 2);
            int rightMedian = findElementAt(nums1, 0, nums2, 0, (l1 + l2 + 1) / 2);

            return (double) (leftMedian + rightMedian) / 2;
        }

        return findElementAt(nums1, 0, nums2, 0, (l1 + l2 - 1) / 2);
    }

    private int findElementAt(int[] nums1, int off1, int[] nums2, int off2, int index) {
        int l1 = nums1.length - off1;
        int l2 = nums2.length - off2;
        int l = index + 1;
        assert(l1 + l2 >= l);

        if (l1 == 0) return nums2[off2 + index];
        if (l2 == 0) return nums1[off1 + index];
        if (l == 1) return Math.min(nums1[off1], nums2[off2]);

        int step1 = Math.min(l / 2, l1);
        int step2 = Math.min(l - step1, l2);

        if (nums1[off1 + step1 - 1] <= nums2[off2 + step2 - 1]) {
            return findElementAt(nums1, off1 + step1, nums2, off2, index - step1);
        } else {
            return findElementAt(nums1, off1, nums2, off2 + step2, index - step2);
        }
    }
}


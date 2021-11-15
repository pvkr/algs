package com.github.pvkr.leet;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Task4Test {

    @ParameterizedTest
    @MethodSource("getTestData")
    void test(int[] nums1, int[] nums2, double expected) {
        double median = new Task4().findMedianSortedArrays(nums1, nums2);

        assertEquals(expected, median);
    }

    private static Stream<Arguments> getTestData() {
        return Stream.of(
                Arguments.arguments(new int[] {1, 3, 4}, new int[] {2}, 2.5),

                Arguments.arguments(new int[] {1, 3, 5, 7}, new int[] {2, 4, 6, 8}, 4.5),
                Arguments.arguments(new int[] {1, 3, 5}, new int[] {2, 4, 6, 8}, 4),
                Arguments.arguments(new int[] {1, 2}, new int[] {3, 4, 5, 6, 7, 8}, 4.5),
                Arguments.arguments(new int[] {4, 5}, new int[] {1, 2, 3, 6, 7, 8}, 4.5),

                Arguments.arguments(new int[] {1, 3}, new int[] {2}, 2.0),
                Arguments.arguments(new int[] {1, 2}, new int[] {3, 4}, 2.5),
                Arguments.arguments(new int[] {0, 0}, new int[] {0, 0}, 0.0),
                Arguments.arguments(new int[] {}, new int[] {1}, 1.0),
                Arguments.arguments(new int[] {2}, new int[] {}, 2.0)
        );
    }
}
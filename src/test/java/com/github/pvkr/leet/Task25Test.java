package com.github.pvkr.leet;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class Task25Test {

    @ParameterizedTest
    @MethodSource("getTestData")
    void test(int[] array, int k, int[] expected) {
        ListNode listNode = ListNode.fromArray(array);
        ListNode merged = new Task25().reverseKGroup(listNode, k);

        assertArrayEquals(expected, ListNode.toArray(merged));
    }

    private static Stream<Arguments> getTestData() {
        return Stream.of(
                Arguments.arguments(new int[] {1,2,3,4,5}, 2, new int[] {2,1,4,3,5}),
                Arguments.arguments(new int[] {1,2,3,4,5}, 3, new int[] {3,2,1,4,5}),
                Arguments.arguments(new int[] {1,2,3,4,5}, 1, new int[] {1,2,3,4,5}),
                Arguments.arguments(new int[] {1}, 1, new int[] {1})
        );
    }
}
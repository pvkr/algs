package com.github.pvkr.leet;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class Task23Test {

    @ParameterizedTest
    @MethodSource("getTestData")
    void test(int[][] kLists, int[] expected) {
        ListNode[] listNodes = Stream.of(kLists).map(Task23Test::toListNode).toArray(ListNode[]::new);
        ListNode merged = new Task23().mergeKLists(listNodes);

        assertArrayEquals(expected, fromListNode(merged));
    }

    private static Stream<Arguments> getTestData() {
        return Stream.of(
                Arguments.arguments(kLists("[[1,4,5],[1,3,4],[2,6]]"), list("[1,1,2,3,4,4,5,6]")),
                Arguments.arguments(kLists("[[]]"), list("[]")),
                Arguments.arguments(kLists("[]"), list("[]"))
        );
    }

    private static int[][] kLists(String matrix) {
        if (matrix.equals("[]")) return new int[0][];
        if (matrix.equals("[[]]")) return new int[][] {new int[0]};
        return Arrays.stream(matrix.substring(2, matrix.length() - 2).split("],\\["))
                .map(Task23Test::list)
                .toArray(int[][]::new);
    }

    private static int[] list(String array) {
        if (array.equals("[]")) return new int[0];
        return Arrays.stream(array.replaceAll("( |]|\\[)*", "").split(","))
                .map(Integer::parseInt)
                .mapToInt(Integer::intValue)
                .toArray();
    }

    private static ListNode toListNode(int[] array) {
        ListNode listNode = null;
        for (int i = array.length - 1; i >= 0; i--) {
            listNode = new ListNode(array[i], listNode);
        }
        return listNode;
    }

    private static int[] fromListNode(ListNode listNode) {
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
}
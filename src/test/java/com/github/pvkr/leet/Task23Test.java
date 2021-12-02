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
        ListNode[] listNodes = Stream.of(kLists).map(ListNode::fromArray).toArray(ListNode[]::new);
        ListNode merged = new Task23().mergeKLists(listNodes);

        assertArrayEquals(expected, ListNode.toArray(merged));
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
        return Arrays.stream(array.replaceAll("([:space]|]|\\[)*", "").split(","))
                .map(Integer::parseInt)
                .mapToInt(Integer::intValue)
                .toArray();
    }
}
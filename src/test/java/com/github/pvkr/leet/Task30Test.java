package com.github.pvkr.leet;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class Task30Test {

    @ParameterizedTest
    @MethodSource("getTestData")
    void test(String s, String[] words, int[] expected) {
        int[] actual = new Task30().findSubstring(s, words).stream().mapToInt(Integer::intValue).toArray();

        Arrays.sort(expected);
        Arrays.sort(actual);

        assertArrayEquals(expected, actual);
    }

    private static Stream<Arguments> getTestData() {
        return Stream.of(
                Arguments.arguments("barfoothefoobarman", new String[] {"foo","bar"}, new int[] {0, 9}),
                Arguments.arguments("wordgoodgoodgoodbestword", new String[] {"word","good","best","word"}, new int[0]),
                Arguments.arguments("barfoofoobarthefoobarman", new String[] {"foo","bar", "the"}, new int[] {6, 9, 12}),
                Arguments.arguments("aaaaaa", new String[] {"a","a","a","a","a","a"}, new int[] {0}),
                Arguments.arguments("aaaaaa", new String[] {"a","a","a","a","a"}, new int[] {0, 1}),
                Arguments.arguments("aaaaaa", new String[] {"a","a","a","a"}, new int[] {0, 1, 2}),
                Arguments.arguments("aaaaaa", new String[] {"aa","aa"}, new int[] {0, 1, 2})
        );
    }
}
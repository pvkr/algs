package com.github.pvkr.leet;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class Task10Test {

    @ParameterizedTest
    @MethodSource("getTestData")
    void test(String string, String pattern, boolean expected) {
        boolean isMatch = new Task10().isMatch(string, pattern);

        assertEquals(expected, isMatch);
    }

    private static Stream<Arguments> getTestData() {
        return Stream.of(
                Arguments.arguments("aa", "a", false),
                Arguments.arguments("aa", "a*", true),
                Arguments.arguments("ab", ".*", true),
                Arguments.arguments("aab", "c*a*b", true),
                Arguments.arguments("mississippi", "mis*is*p*.", false),
                Arguments.arguments("aabbbbab", "c*a*b*a*b", true),
                Arguments.arguments("aabb", "c*a*b*a*b", true)
        );
   }
}
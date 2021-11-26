package com.github.pvkr.algs;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExpressionTest {

    @ParameterizedTest
    @MethodSource("getValidExpressions")
    void testValidExpressions(String expression, double expected) {
        double result = new Expression(expression).parse();

        assertEquals(expected, result);
    }

    private static Stream<Arguments> getValidExpressions() {
        return Stream.of(
                Arguments.arguments("1+2-4*3/6", 1.0),
                Arguments.arguments("1+(2-4)*3/6", 0.0),
                Arguments.arguments("1+(2-4 )*3/-(6) ", 2.0)
        );
    }

    @ParameterizedTest
    @MethodSource("getInvalidExpressions")
    void testInvalidExpressions(String expression, String errorMessage) {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> new Expression(expression).parse());

        assertEquals(errorMessage, exception.getMessage());
    }

    private static Stream<Arguments> getInvalidExpressions() {
        return Stream.of(
                Arguments.arguments("1+(2-4 )*(/-6 ", "Unexpected character '/' at position 10"),
                Arguments.arguments("1+(2-4 )*3/6 +", "Unexpected character '\uFFFF' at position 14"),
                Arguments.arguments("1+(2-4 )*3/6 + *", "Unexpected character '*' at position 15")
        );
    }
}
package com.github.pvkr.algs;

public class Expression {
    private final String expression;
    private int pos;
    private int ch;

    public Expression(String expression) {
        this.expression = expression;
        this.pos = -1;
        this.ch = -1;
    }

    public double parse() {
        nextChar();
        double value = parseExpression();
        if (pos < expression.length())
            throw new RuntimeException("Unexpected character '" + (char)ch + "' at position " + pos);
        return value;
    }

    private double parseExpression() {
        double value = parseTerm();
        while (true) {
            if (next('+')) value += parseTerm();
            else if (next('-')) value -= parseTerm();
            else return value;
        }
    }

    private double parseTerm() {
        double value = parseFactor();
        while (true) {
            if (next('*')) value *= parseFactor();
            else if (next('/')) value /= parseFactor();
            else return value;
        }
    }

    private double parseFactor() {
        if (next('+')) return parseFactor();
        if (next('-')) return -parseFactor();

        double value;
        if (next('(')) {
            value = parseExpression();
            next(')');
        } else if (isNumberCharacter(ch)) {
            int start = pos;
            while (isNumberCharacter(ch)) nextChar();
            value = Double.parseDouble(expression.substring(start, pos));
        } else {
            throw new RuntimeException("Unexpected character '" + (char)ch + "' at position " + pos);
        }

        return value;
    }

    private boolean next(char next) {
        while (ch == ' ') nextChar();
        if (ch == next) {
            nextChar();
            return true;
        }
        return false;
    }

    private void nextChar() {
        ch = (++pos < expression.length()) ? expression.charAt(pos) : -1;
    }

    private boolean isNumberCharacter(int ch) {
        return ch >= '0' && ch <= '9' || ch == '.';
    }
}

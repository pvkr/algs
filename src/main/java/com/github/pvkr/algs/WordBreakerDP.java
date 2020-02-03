package com.github.pvkr.algs;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordBreakerDP {
    private final Set<String> vocabulary;

    public WordBreakerDP(String[] vocabulary) {
        this.vocabulary = Stream.of(vocabulary).collect(Collectors.toSet());
    }

    public boolean parse(String sentence) {
        int size = sentence.length();
        boolean[] dp = new boolean[size];
        for (int i = 0; i < size; i++) {
            if (!dp[i] && vocabulary.contains(sentence.substring(0, i + 1))) {
                dp[i] = true;
            }

            if (dp[i]) {
                if (i == size - 1) break;

                for (int j = i + 1; j < size; j++) {
                    if (!dp[j] && vocabulary.contains(sentence.substring(i + 1, j + 1))) {
                        dp[j] = true;
                    }
                }
            }
        }

        return dp[size - 1];
    }

    public static void main(String[] args) {
        test("thisisaword", new String[] {"this", "i", "is", "a", "saw", "or", "word"});
        test("thisisaword", new String[] {"this", "i", "is", "saw", "or", "word"});
    }

    private static void test(String sentence, String[] vocabulary) {
        WordBreakerDP wordBreaker = new WordBreakerDP(vocabulary);
        System.out.println("Parsed: " + wordBreaker.parse(sentence));
    }
}

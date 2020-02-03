package com.github.pvkr.algs;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class WordBreaker {
    private final Set<String> vocabulary;

    public WordBreaker(String[] vocabulary) {
        this.vocabulary = Stream.of(vocabulary).collect(Collectors.toSet());
    }

    public String[] parse(String sentence) {
        ArrayDeque<String> words = new ArrayDeque<>();
        if (parse(sentence, 0, words)) {
            return StreamSupport.stream(
                    Spliterators.spliteratorUnknownSize(words.descendingIterator(), Spliterator.ORDERED), false)
                    .toArray(String[]::new);
        }

        return new String[0];
    }

    private boolean parse(String sentence, int offset, ArrayDeque<String> words) {
        if (offset == sentence.length())
            return true;
        for (String word : findWords(sentence, offset)) {
            words.push(word);
            if (parse(sentence, offset + word.length(), words))
                return true;
            words.pop();
        }
        return false;
    }

    private List<String> findWords(String sentence, int offset) {
        List<String> words = new ArrayList<>();
        int pos = offset;
        while (pos++ < sentence.length()) {
            String word = sentence.substring(offset, pos);
            if (vocabulary.contains(word))
                words.add(word);
        }
        return words;
    }

    public static void main(String[] args) {
        test("thisisaword", new String[] {"this", "i", "is", "a", "saw", "or", "word"});
        test("thisisaword", new String[] {"this", "i", "is", "saw", "or", "word"});
    }

    private static void test(String sentence, String[] vocabulary) {
        WordBreaker wordBreaker = new WordBreaker(vocabulary);
        String[] parsedSentence = wordBreaker.parse(sentence);
        System.out.println("Parsed: " + (parsedSentence.length > 0));
        System.out.println("Result: " + String.join(" ", parsedSentence));
    }
}

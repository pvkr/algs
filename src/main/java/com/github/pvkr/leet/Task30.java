package com.github.pvkr.leet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Constraints:
 *  1 <= s.length <= 10^4
 *  s consists of lower-case English letters.
 *  1 <= words.length <= 5000
 *  1 <= words[i].length <= 30
 *  words[i] consists of lower-case English letters.
 */
public class Task30 {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>(10);

        int wlen = words[0].length();
        for (int i = 0; i < wlen; i++) {
            Map<String, Integer> wordMap = createWordMap(words);
            int wordCount = 0;
            int wordsInS = (s.length() - i) / wlen;
            for (int j = 0; j < wordsInS; j++) {
                wordCount++;
                if (wordCount > words.length) {
                    pushBack(wordMap, s, i, j - words.length, wlen);
                    wordCount--;
                }

                int wordIndex = j * wlen + i;
                String word = s.substring(wordIndex, wordIndex + wlen);

                Integer occurrences = wordMap.get(word);
                if (occurrences != null) {
                    wordMap.put(word, occurrences - 1);
                } else {
                    // restart search
                    for (int k = j - 1; k > j - wordCount; k--)
                        pushBack(wordMap, s, i, k, wlen);
                    wordCount = 0;
                    continue;
                }

                if (wordCount >= words.length && containsOnlyZeroes(wordMap))
                    result.add((j - words.length + 1) * wlen + i);
            }
        }

        return result;
    }

    private void pushBack(Map<String, Integer> wordMap, String s, int wordOffset, int wordIndex, int wordSize) {
        int offset = wordIndex * wordSize + wordOffset;
        String pushBackWord = s.substring(offset, offset + wordSize);
        Integer occurrences = wordMap.get(pushBackWord);
        wordMap.put(pushBackWord, occurrences + 1);
    }

    private Map<String, Integer> createWordMap(String[] words) {
        Map<String, Integer> wordMap = new HashMap<>(words.length);
        for (String word : words) {
            Integer occurrences = wordMap.getOrDefault(word, 0);
            wordMap.put(word, occurrences + 1);
        }

        return wordMap;
    }

    private boolean containsOnlyZeroes(Map<String, Integer> wordMap) {
        for (Integer occurrences : wordMap.values()) {
            if (occurrences != 0) return false;
        }
        return true;
    }
}

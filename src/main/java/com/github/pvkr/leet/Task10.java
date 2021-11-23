package com.github.pvkr.leet;

/**
 * Constraints:
 *  1 <= s.length <= 20
 *  1 <= p.length <= 30
 *  s contains only lowercase English letters.
 *  p contains only lowercase English letters, '.', and '*'.
 * It is guaranteed for each appearance of the character '*', there will be a previous valid character to match.
 */
public class Task10 {
    public boolean isMatch(String s, String p) {
        return isMatch(s, 0, p, 0);
    }

    private boolean isMatch(String s, int sOffset, String p, int pOffset) {
        var fixedSizePattern = nextFixedSizePattern(p, pOffset);
        if (fixedSizePattern.off == -1) {
            return isMatchStarsPattern(s, sOffset, s.length(), p, pOffset, p.length());
        }

        int index = indexOf(s, sOffset, p, fixedSizePattern.off, fixedSizePattern.len);
        if (fixedSizePattern.off == pOffset && index != sOffset) {
            return false;
        }

        while (index != -1) {
            if (isMatchStarsPattern(s, sOffset, index, p, pOffset, fixedSizePattern.off)
                    && isMatch(s, index + fixedSizePattern.len, p, fixedSizePattern.off + fixedSizePattern.len)) {
                return true;
            }

            index = indexOf(s, index + 1, p, fixedSizePattern.off, fixedSizePattern.len);
        }

        return false;
    }

    private Chunk<Integer, Integer> nextFixedSizePattern(String p, int pOffset) {
        int starIndex = p.indexOf('*', pOffset);
        while (starIndex == pOffset + 1) {
            pOffset += 2;
            starIndex = p.indexOf('*', pOffset);
        }

        if (starIndex != -1) {
            return new Chunk<>(pOffset, starIndex - pOffset - 1);
        }
        else if (p.length() > pOffset) {
            return new Chunk<>(pOffset, p.length() - pOffset);
        }
        else {
            return new Chunk<>(-1, 0);
        }
    }

    private int indexOf(String s, int sOffset, String p, int pOffset, int pLen) {
        loop: for (int index = sOffset; index + pLen <= s.length(); index++) {
            for (int offset = 0; offset < pLen; offset++) {
                if (!isMatchChar(s.charAt(index + offset), p.charAt(pOffset + offset))) {
                    continue loop;
                }
            }

            return index;
        }

        return -1;
    }

    // Stars Pattern -  [c1]*[c2]*... pattern
    private boolean isMatchStarsPattern(String s, int sFrom, int sTo, String p, int pFrom, int pTo) {
        while (sFrom < sTo && pFrom < pTo) {
            if (isMatchChar(s.charAt(sFrom), p.charAt(pFrom))) sFrom++;
            else pFrom += 2;
        }
        return sFrom >= sTo;
    }

    private boolean isMatchChar(char c, char p) {
        return c == p || p == '.';
    }

    private static class Chunk<X, Y> {
        public final X off;
        public final Y len;
        public Chunk(X off, Y len) {
            this.off = off;
            this.len = len;
        }
    }
}

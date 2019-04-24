package com.github.pvkr.algs;

public class MaxPalindromeSubstring {
    public static void main(String[] args) {
        System.out.println(getMaxPalindromeD("banana"));
        System.out.println(getMaxPalindromeD("ban"));
        System.out.println(getMaxPalindromeD("bannobc"));
    }

    // first
    private static String getMaxPalindromeF(String text) {
        char[] chars = text.toCharArray();

        int excludedIdx;
        for (excludedIdx = 0; excludedIdx < chars.length; excludedIdx++) {
            if (isPalindrome(chars, excludedIdx))
                break;
        }

        if (excludedIdx < 0 || excludedIdx >= chars.length)
            return text.length() > 0 ? text.substring(0, 1) : "";

        return (excludedIdx > 0 ? String.valueOf(chars, 0, excludedIdx) : "")
                + (excludedIdx < chars.length - 1 ? String.valueOf(chars, excludedIdx + 1, chars.length - excludedIdx - 1) : "");
    }

    private static boolean isPalindrome(char[] chars, int excludedIdx) {
        int i = 0, j = chars.length - 1;
        while (i < j) {
            if (i == excludedIdx) i++;
            if (j == excludedIdx) j--;
            if (i > j || chars[i] != chars[j])
                return false;
            i++;
            j--;
        }

        return true;
    }

    // recursive
    private static String getMaxPalindromeR(String text) {
        return getMaxPalindromeRecursive(text.toCharArray(), 0, text.length() - 1);
    }

    private static String getMaxPalindromeRecursive(char[] chars, int i, int j) {
        if (i > j || i >= chars.length || j < 0) return "";
        if (i == j) return String.valueOf(chars[i]);

        if (chars[i] == chars[j]) {
            return chars[i] + getMaxPalindromeRecursive(chars, i + 1, j - 1) + chars[i];
        }

        String palindrome1 = getMaxPalindromeRecursive(chars, i + 1, j);
        String palindrome2 = getMaxPalindromeRecursive(chars, i, j - 1);

        return palindrome1.length() >= palindrome2.length() ? palindrome1 : palindrome2;
    }

    // dynamic
    private static String getMaxPalindromeD(String text) {
        char[] chars = text.toCharArray();
        int n = chars.length;
        String[] palindromes = new String[n * n];

        for (int i = 0; i < n; i++) palindromes[i + i * n] = String.valueOf(chars[i]);

        for (int k = 1; k < n; k++) {
            for (int i = 0; i + k < n; i++) {
                int j = i + k;
                if (chars[i] == chars[j]) {
                    if (i + 1 <= j - 1)
                        palindromes[i + j * n] = chars[i] + palindromes[(i + 1) + (j - 1) * n] + chars[j];
                    else
                        palindromes[i + j * n] = chars[i] + "" + chars[j];
                } else {
                    String p1 = palindromes[i + 1 + j * n];
                    String p2 = palindromes[i + (j - 1) * n];
                    palindromes[i + j * n] = p1.length() >= p2.length() ? p1 : p2;
                }
            }
        }

        return palindromes[(n - 1) * n];
    }
}

package com.github.pvkr.algs;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Collections.singletonList;

public class Permutation {

    public String[] getPermutations(String value) {
        return permutations(value.length()).stream()
                .map(permutation -> {
                    char[] chars = new char[value.length()];
                    for (int i = 0; i < value.length(); i++) {
                        chars[i] = value.charAt(permutation.get(i));
                    }

                    return new String(chars);
                })
                .toArray(String[]::new);
    }

    private List<List<Integer>> permutations(int n) {
        if (n == 1)
            return singletonList(singletonList(0));

        return permutations(n - 1).stream()
                .flatMap(permutation ->
                        IntStream.range(0, n).boxed().map(i -> {
                            List<Integer> copy = new ArrayList<>(permutation);
                            copy.add(i, n - 1);
                            return copy;
                        })
                )
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        test("CAP");
    }

    private static void test(String str) {
        Permutation permutation = new Permutation();
        System.out.println(String.join(", ", permutation.getPermutations(str)));
    }
}

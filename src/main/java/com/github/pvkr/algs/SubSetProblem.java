package com.github.pvkr.algs;

import java.util.ArrayDeque;

public class SubSetProblem {

    private int[] values;
    private int sum;
    private boolean[][] bbData;

    public SubSetProblem(int[] values, int sum) {
        this.values = values;
        this.sum = sum;
    }

    private void solve() {
        calculateBranchAndBoundData(values, sum);
        findSubsetsAndPrint(values, values.length - 1, sum, new ArrayDeque<>());
    }

    private void calculateBranchAndBoundData(int[] values, int sum) {
        boolean[][] bbData = new boolean[values.length][sum + 1];

        for (int s = 0; s <= sum; s++)
            bbData[0][s] = values[0] == s;

        for (int i = 1; i < values.length; i++) {
            for (int s = 0; s <= sum; s++) {
                bbData[i][s] = bbData[i - 1][s] || (s >= values[i] && bbData[i - 1][s - values[i]]);
            }
        }

        this.bbData = bbData;
    }

    private void findSubsetsAndPrint(int[] values, int idx, int sum, ArrayDeque<Integer> subset) {
        if (idx == 0 && sum != 0 && bbData[0][sum]) {
            subset.push(values[0]);
            print(subset);
            subset.pop();
            return;
        }

        if (sum == 0) {
            print(subset);
            return;
        }

        if (idx > 0 && bbData[idx][sum]) {
            findSubsetsAndPrint(values, idx - 1, sum, subset);

            if (sum >= values[idx]) {
                subset.push(values[idx]);
                findSubsetsAndPrint(values, idx - 1, sum - values[idx], subset);
                subset.pop();
            }
        }
    }

    private void print(ArrayDeque<Integer> subset) {
        System.out.println(subset);
    }

    public static void main(String[] args) {
        SubSetProblem subSetProblem = new SubSetProblem(new int[] {2, 3, 5, 6, 8, 10}, 10);
        subSetProblem.solve();
    }
}
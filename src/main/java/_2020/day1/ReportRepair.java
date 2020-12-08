package _2020.day1;

import lombok.extern.java.Log;
import util.SolvableTask;

import java.util.Arrays;

@Log
public class ReportRepair implements SolvableTask {

    private static final int SEARCHABLE_SUM = 2020;

    public static void main(String[] args) {
        new ReportRepair().solve();
    }

    @Override
    public void solve() {
        int[] input = Arrays.stream(getInputLines())
                .mapToInt(Integer::parseInt)
                .toArray();

        Triple triple = findSumPair(input, SEARCHABLE_SUM);
        int result = multiply(triple);

        log.info(String.valueOf(result));
    }

    public static Triple findSumPair(int[] input, int sumToFind) {
        for (int i = 0; i < input.length - 2; i++) {
            for (int j = i + 1; j < input.length - 1; j++) {
                for (int k = j + 1; k < input.length; k++) {
                    if (input[i] + input[j] + input[k] == sumToFind) {
                        return new Triple(input[i], input[j], input[k]);
                    }
                }
            }
        }

        throw new RuntimeException("Pair not found!");
    }

    public static int multiply(Triple triple) {
        return triple.getFirst() * triple.getSecond() * triple.getThird();
    }

}

package day1;

import util.FileReader;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;

public class ReportRepair {

    private static final int SEARCHABLE_SUM = 2020;

    public static void main(String[] args) {
        int[] input = Arrays.stream(getInput().split("\n"))
                .mapToInt(Integer::parseInt)
                .toArray();

        Triple triple = findSumPair(input, SEARCHABLE_SUM);
        int result = multiply(triple);

        System.out.println(result);
    }

    private static String getInput() {
        String day = MethodHandles.lookup().lookupClass().getPackageName();
        return FileReader.readInput(day);
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

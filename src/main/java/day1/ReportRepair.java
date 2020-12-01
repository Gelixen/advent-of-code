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

        Pair<Integer, Integer> pair = findSumPair(input, SEARCHABLE_SUM);
        int result = multiply(pair);

        System.out.println(result);
    }

    private static String getInput() {
        String day = MethodHandles.lookup().lookupClass().getPackageName();
        return FileReader.readInput(day);
    }

    public static Pair<Integer, Integer> findSumPair(int[] input, int sumToFind) {
        for (int i = 0; i < input.length - 1; i++) {
            for (int j = i + 1; j < input.length; j++) {
                if (input[i] + input[j] == sumToFind) {
                    return new Pair<>(input[i], input[j]);
                }
            }
        }

        throw new RuntimeException("Pair not found!");
    }

    public static int multiply(Pair<Integer, Integer> pair) {
        return pair.getFirst() * pair.getSecond();
    }

}

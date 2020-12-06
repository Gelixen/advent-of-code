package _2020.day6;

import lombok.extern.java.Log;
import util.SolvableTask;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;

@Log
public class CustomCustoms implements SolvableTask {

    public static void main(String[] args) {
        new CustomCustoms().solve();
    }

    public static long sumGroupsAnswers(String[] inputLines) {
        return Arrays.stream(inputLines)
                .mapToLong(CustomCustoms::countDistinctGroupAnswers)
                .sum();
    }

    public static long countDistinctGroupAnswers(String groupAnswers) {
        return groupAnswers.chars()
                .distinct()
                .filter(CustomCustoms::isNotNewLine)
                .count();
    }

    private static boolean isNotNewLine(int symbol) {
        return symbol != '\n';
    }

    @Override
    public String getPackageName() {
        return MethodHandles.lookup().lookupClass().getPackageName();
    }

    @Override
    public void solve() {
        long result = sumGroupsAnswers(getInputLines("\n\n"));

        log.info(String.valueOf(result));
    }

}

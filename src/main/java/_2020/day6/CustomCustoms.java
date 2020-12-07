package _2020.day6;

import lombok.extern.java.Log;
import util.SolvableTask;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Log
public class CustomCustoms implements SolvableTask {

    public static void main(String[] args) {
        new CustomCustoms().solve();
    }

    public static long sumGroupsAgreedAnswers(String[] inputLines) {
        return Arrays.stream(inputLines)
                .mapToLong(CustomCustoms::countGroupAgreedAnswers)
                .sum();
    }

    public static long countGroupAgreedAnswers(String groupAnswers) {
        Map<Integer, Long> lettersOccurrenceMap = groupAnswers.chars()
                .filter(CustomCustoms::isNotNewLine)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        int peopleInGroupCount = groupAnswers.split("\n").length;

        return lettersOccurrenceMap.values().stream()
                .filter(letterOccurrence -> peopleInGroupCount == letterOccurrence)
                .count();
    }

    private static boolean isNotNewLine(int symbol) {
        return symbol != '\n';
    }

    @Override
    public void solve() {
        long result = sumGroupsAgreedAnswers(getInputLines("\n\n"));

        log.info(String.valueOf(result));
    }

}

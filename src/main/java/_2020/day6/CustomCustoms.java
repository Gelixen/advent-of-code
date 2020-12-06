package _2020.day6;

import lombok.extern.java.Log;
import util.SolvableTask;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Log
public class CustomCustoms implements SolvableTask {

    @Override
    public String getPackageName() {
        return MethodHandles.lookup().lookupClass().getPackageName();
    }

    public static void main(String[] args) {
        new CustomCustoms().solve();
    }

    @Override
    public void solve() {
        long result = sumGroupsAgreedAnswers(getInputLines("\n\n"));

        log.info(String.valueOf(result));
    }

    public static long sumGroupsAgreedAnswers(String[] inputLines) {
        return Arrays.stream(inputLines)
                .mapToLong(CustomCustoms::countGroupAgreedAnswers)
                .sum();
    }

    public static long countGroupAgreedAnswers(String groupAnswers) {
        Map<Integer, Long> answersOccurrenceMap = collectAnswersOccurrenceMap(groupAnswers);

        int peopleInGroupCount = groupAnswers.split("\n").length;

        return answersOccurrenceMap.values().stream()
                .filter(answerOccurrence -> peopleInGroupCount == answerOccurrence)
                .count();
    }

    private static Map<Integer, Long> collectAnswersOccurrenceMap(String groupAnswers) {
        return groupAnswers.chars()
                .filter(CustomCustoms::isNotNewLine)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    private static boolean isNotNewLine(int symbol) {
        return symbol != '\n';
    }

}

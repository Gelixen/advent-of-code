package _2022.day3;

import lombok.extern.java.Log;
import util.SolvableTask;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

@Log
public class RucksackReorganization implements SolvableTask {

    private static final int LOWERCASE_BASE = 96;
    private static final int UPPERCASE_BASE = 38;

    public static void main(String[] args) {
        new RucksackReorganization().solve();
    }

    @Override
    public void solve() {
        String[] rucksacks = getInputLines();

        int findSum = IntStream.range(0, rucksacks.length)
                .boxed()
                .collect(groupByConsecutiveThree(rucksacks))
                .values()
                .stream()
                .map(this::mapToGroup)
                .map(this::findCommon)
                .mapToInt(this::rebaseAsIntValue)
                .sum();

        System.out.println(findSum);
    }

    private static Collector<Integer, ?, Map<Integer, List<String>>> groupByConsecutiveThree(String[] rucksacks) {
        return groupingBy(index -> index / 3, mapping(idx -> rucksacks[idx], toList()));
    }

    private Group mapToGroup(List<String> lines) {
        String firstElf = lines.get(0);
        String secondElf = lines.get(1);
        String thirdElf = lines.get(2);

        return new Group(firstElf, secondElf, thirdElf);
    }

    private Character findCommon(Group group) {
        Set<Character> firstElfItems = group.firstElf().chars().mapToObj(e -> (char) e).collect(toSet());
        Set<Character> secondElfItems = group.secondElf().chars().mapToObj(e -> (char) e).collect(toSet());
        Set<Character> thirdElfItems = group.thirdElf().chars().mapToObj(e -> (char) e).collect(toSet());

        firstElfItems.retainAll(secondElfItems);
        firstElfItems.retainAll(thirdElfItems);

        return firstElfItems.iterator().next();
    }

    private int rebaseAsIntValue(Character character) {
        return (Character.isLowerCase(character)) ? character - LOWERCASE_BASE : character - UPPERCASE_BASE;
    }
}

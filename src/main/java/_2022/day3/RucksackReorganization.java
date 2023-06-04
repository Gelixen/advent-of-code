package _2022.day3;

import lombok.extern.java.Log;
import util.SolvableTask;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        int findSum = Stream.of(rucksacks)
                .map(this::splitIntoCompartments)
                .map(this::findError)
                .mapToInt(this::rebaseAsIntValue)
                .sum();

        System.out.println(findSum);
    }

    private Rucksack splitIntoCompartments(String line) {
        int middleBreakPoint = line.length() / 2;
        String first = line.substring(0, middleBreakPoint);
        String second = line.substring(middleBreakPoint);

        return new Rucksack(first, second);
    }

    private Character findError(Rucksack rucksack) {
        Set<Character> first = rucksack.firstCompartment().chars().mapToObj(e -> (char) e).collect(Collectors.toSet());
        Set<Character> second = rucksack.secondCompartment().chars().mapToObj(e -> (char) e).collect(Collectors.toSet());
        first.retainAll(second);
        return first.iterator().next();
    }

    private int rebaseAsIntValue(Character character) {
        return (Character.isLowerCase(character)) ? character - LOWERCASE_BASE : character - UPPERCASE_BASE;
    }
}

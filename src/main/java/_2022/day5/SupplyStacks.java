package _2022.day5;

import lombok.extern.java.Log;
import util.SolvableTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.function.Predicate.not;

@Log
public class SupplyStacks implements SolvableTask {

    private static final int STACK_DATA_BLOCK_SIZE = 4;

    public static void main(String[] args) {
        new SupplyStacks().solve();
    }

    @Override
    public void solve() {
        String[] lines = getInputLines();

        InputData inputData = getInputData(lines);

        useCrane(inputData);

        log.info(getFirstCratesFromStacks(inputData));
    }

    private static String getFirstCratesFromStacks(InputData inputData) {
        return inputData.stacksOfCrates().stream()
                .map(LinkedList::getFirst)
                .map(Object::toString)
                .reduce("", String::concat);
    }

    private static InputData getInputData(String[] lines) {
        List<LinkedList<Character>> stacksOfCrates = getStacksOfCrates(lines);
        List<RearrangementProcedure> rearrangementProcedures = getRearrangementProcedures(lines);

        return new InputData(stacksOfCrates, rearrangementProcedures);
    }

    private void useCrane(InputData inputData) {
        List<LinkedList<Character>> stacksOfCrates = inputData.stacksOfCrates();

        inputData.rearrangementProcedures()
                .forEach(procedure -> IntStream.range(0, procedure.amount())
                        .forEach(__ -> {
                            Character crateToMove = stacksOfCrates.get(procedure.from() - 1).pop();
                            stacksOfCrates.get(procedure.to() - 1).push(crateToMove);
                        }));
    }

    private static List<LinkedList<Character>> getStacksOfCrates(String[] lines) {
        List<LinkedList<Character>> stacksOfCrates = IntStream.range(0, lines[0].length() / STACK_DATA_BLOCK_SIZE + 1)
                .mapToObj(i -> new LinkedList<Character>())
                .collect(Collectors.toCollection(ArrayList::new));

        long stackDataLinesCountExcludingNumberRow = Arrays.stream(lines)
                .takeWhile(not(String::isEmpty))
                .count() - 1;

        Arrays.stream(lines)
                .limit(stackDataLinesCountExcludingNumberRow)
                .forEach(line -> {
                    for (int i = 1; i < line.length(); i += STACK_DATA_BLOCK_SIZE) {
                        char symbol = line.charAt(i);
                        if (symbol != ' ') {
                            stacksOfCrates.get(i / STACK_DATA_BLOCK_SIZE).add(symbol);
                        }
                    }
                });
        return stacksOfCrates;
    }

    private static List<RearrangementProcedure> getRearrangementProcedures(String[] lines) {
        long nonProcedureDataLinesCount = Arrays.stream(lines)
                .takeWhile(not(String::isEmpty))
                .count() + 1;

        return Arrays.stream(lines)
                .skip(nonProcedureDataLinesCount)
                .map(RearrangementProcedureExtractor::new)
                .map(RearrangementProcedureExtractor::extract)
                .collect(Collectors.toList());
    }

}
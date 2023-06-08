package _2022.day11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import lombok.extern.java.Log;
import util.SolvableTask;

@Log
public class MonkeyInTheMiddle implements SolvableTask {


    private static final int MAX_ROUNDS = 20;
    private static final int MONKEY_DATA_ROWS_COUNT = 7;

    public static void main(String[] args) {
        new MonkeyInTheMiddle().solve();
    }

    @Override
    public void solve() {
        String[] inputLines = getInputLines();

        List<Monkey> monkeys = fillMonkeysData(inputLines);

        int[] itemInspectionCountByMonkey = iterateRoundsAndReturnInspectionsCount(monkeys);

        Integer top2InspectionsMultiplied = Arrays.stream(itemInspectionCountByMonkey)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .limit(2)
                .reduce((first, second) -> first * second)
                .orElseThrow();
        
        log.info(String.valueOf(top2InspectionsMultiplied));

    }

    private static int[] iterateRoundsAndReturnInspectionsCount(List<Monkey> monkeys) {
        int[] itemInspectionCountByMonkey = new int[monkeys.size()];

        for (int round = 0; round < MAX_ROUNDS; round++) {
            for (Monkey monkey : monkeys) {
                monkey.worryLevels().forEach(level -> {
                    int levelAfterInspection = monkey.operation().apply(level);
                    int levelAfterBored = levelAfterInspection / 3;

                    Test test = monkey.test();
                    int passOnTo = levelAfterBored % test.divisor() == 0
                            ? test.passToOnTrue()
                            : test.passToOnFalse();

                    monkeys.get(passOnTo).worryLevels().add(levelAfterBored);
                });

                itemInspectionCountByMonkey[monkey.id()] += monkey.worryLevels().size();
                monkey.worryLevels().clear();
            }
            printMonkeyLevels(round + 1, monkeys);
        }
        return itemInspectionCountByMonkey;
    }

    private List<Monkey> fillMonkeysData(String[] inputLines) {
        List<Monkey> monkeys = new ArrayList<>();

        for (int i = 0; i < inputLines.length; i += MONKEY_DATA_ROWS_COUNT) {
            int id = parseId(inputLines[i]);
            List<Integer> worryLevels = parseWorryLevels(inputLines[i + 1]);
            UnaryOperator<Integer> operation = parseOperation(inputLines[i + 2]);
            int divisor = parseDivisor(inputLines[i + 3]);
            int passToOnTrue = parsePassTo(inputLines[i + 4]);
            int passToOnFalse = parsePassTo(inputLines[i + 5]);

            Test test = new Test(divisor, passToOnTrue, passToOnFalse);
            Monkey monkey = new Monkey(id, worryLevels, operation, test);

            monkeys.add(monkey);
        }

        printMonkeys(monkeys);

        return monkeys;
    }

    private static void printMonkeys(List<Monkey> monkeys) {
        for (Monkey monkey : monkeys) {
            System.out.println(monkey);
        }
        System.out.println();
    }

    private static void printMonkeyLevels(int round, List<Monkey> monkeys) {
        System.out.println("After round " + round + ":");
        for (Monkey m : monkeys) {
            System.out.println(m.id() + " " + m.worryLevels());
        }
        System.out.println();
    }

    private int parsePassTo(String inputLine) {
        int passToIndex = inputLine.indexOf("monkey") + 7;
        String passTo = inputLine.substring(passToIndex);

        return Integer.parseInt(passTo);
    }

    private int parseDivisor(String inputLine) {
        int divisorIndex = inputLine.indexOf("by ") + 3;
        String divisor = inputLine.substring(divisorIndex);

        return Integer.parseInt(divisor);
    }

    private UnaryOperator<Integer> parseOperation(String inputLine) {
        int worryLevelsStartIndex = inputLine.indexOf("= old") + 6;
        String equationString = inputLine.substring(worryLevelsStartIndex);
        String[] equationParts = equationString.split(" ");

        BinaryOperator<Integer> binaryOperator = switch (equationParts[0]) {
            case "+" -> Integer::sum;
            case "*" -> (a, b) -> a * b;
            default -> throw new IllegalStateException("Unexpected value: " + equationParts[0]);
        };

        return switch (equationParts[1]) {
            case "old" -> (operand) -> binaryOperator.apply(operand, operand);
            default ->
                    (operand) -> binaryOperator.apply(operand, Integer.parseInt(equationParts[1]));
        };
    }

    private List<Integer> parseWorryLevels(String inputLine) {
        int worryLevelsStartIndex = inputLine.indexOf(":") + 2;
        String worryLevelsLine = inputLine.substring(worryLevelsStartIndex);
        String[] worryLevels = worryLevelsLine.split(", ");

        return Arrays.stream(worryLevels)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private static int parseId(String inputLines) {
        String id = inputLines.substring(inputLines.indexOf(" ") + 1, inputLines.indexOf(":"));
        return Integer.parseInt(id);
    }

}
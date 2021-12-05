package _2021.day3;

import lombok.extern.java.Log;
import util.SolvableTask;

import java.util.*;

@Log
public class BinaryDiagnostic implements SolvableTask {

    private static final char KEY_ZERO = '0';
    private static final char KEY_ONE = '1';

    public BinaryDiagnostic() {}

    public static void main(String[] args) {
        new BinaryDiagnostic().solve();
    }

    @Override
    public void solve() {
        List<String> binaryNumbers = Arrays.stream(getInputLines()).toList();

        int oxygenGeneratorRating = toDecimal(calculateRating(binaryNumbers, 0, true));
        int co2ScrubberRating = toDecimal(calculateRating(binaryNumbers, 0, false));

        log.info(String.valueOf(oxygenGeneratorRating));
        log.info(String.valueOf(co2ScrubberRating));
        log.info(String.valueOf(oxygenGeneratorRating * co2ScrubberRating));
    }

    public String calculateRating(List<String> numbers, int position, boolean isOxygenRating) {
        if (numbers.size() == 1) {
            return numbers.get(0);
        }

        Map<Character, List<String>> repetitions = collectRepetitions(numbers, position);

        int signum = Integer.signum(repetitions.get(KEY_ZERO).size() - repetitions.get(KEY_ONE).size()) * getDefaultDeterminant(isOxygenRating);
        position++;

        return switch (signum) {
            case 1:
                yield calculateRating(repetitions.get(KEY_ZERO), position, isOxygenRating);
            case 0:
                yield calculateRating(repetitions.get(getDefaultDigitFilter(isOxygenRating)), position, isOxygenRating);
            case -1:
                yield calculateRating(repetitions.get(KEY_ONE), position, isOxygenRating);
            default:
                throw new IllegalStateException("Unexpected value: " + signum);
        };
    }

    private int getDefaultDeterminant(boolean isOxygenRating) {
        return isOxygenRating ? 1 : -1;
    }

    private Character getDefaultDigitFilter(boolean isOxygenRating) {
        return isOxygenRating ? KEY_ONE : KEY_ZERO;
    }

    private Map<Character, List<String>> collectRepetitions(List<String> numbers, int position) {
        Map<Character, List<String>> repetitions = new HashMap<>() {
            {
                put(KEY_ZERO, new ArrayList<>());
                put(KEY_ONE, new ArrayList<>());
            }
        };

        for (String number : numbers) {
            char digit = number.charAt(position);

            repetitions.computeIfPresent(digit, (key, value) -> {
                value.add(number);
                return value;
            });
        }

        return repetitions;
    }

    private int toDecimal(String binary) {
        return Integer.parseInt(binary, 2);
    }
}

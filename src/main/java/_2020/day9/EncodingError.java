package _2020.day9;

import lombok.extern.java.Log;
import util.SolvableTask;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Arrays.copyOf;
import static java.util.Arrays.stream;

@Log
public class EncodingError implements SolvableTask {

    private static final int DEFAULT_PREAMBLE_SIZE = 25;

    private final Long[] numbers = stream(getInputLines()).map(Long::parseLong).toArray(Long[]::new);
    private List<Long> preamble = new LinkedList<>(asList(copyOf(numbers, DEFAULT_PREAMBLE_SIZE)));

    EncodingError changePreamble(int preambleSize) {
        preamble = new LinkedList<>(asList(copyOf(numbers, preambleSize)));
        return this;
    }

    public static void main(String[] args) {
        new EncodingError().solve();
    }

    @Override
    public void solve() {
        long invalidNumber = checkAllNumbers();
        Long[] contiguousNumbers = findContiguousNumbersSumSet(invalidNumber);
        long result = findMinMaxSum(contiguousNumbers);
        log.info(String.valueOf(result));
    }

    Long checkAllNumbers() {
        int currentIndex = preamble.size();

        while (currentIndex < numbers.length) {
            Long newNumber = numbers[currentIndex];
            if (sumPairExists(newNumber)) {
                preamble.remove(0);
                preamble.add(newNumber);
            } else {
                return newNumber;
            }
            currentIndex++;
        }

        return -1L;
    }

    boolean sumPairExists(long sumToSearch) {
        return preamble.stream().anyMatch(number -> {
            long remainder = sumToSearch - number;
            if (remainder != number) {
                return preamble.contains(remainder);
            } else {
                return false;
            }
        });
    }

    Long[] findContiguousNumbersSumSet(long searchableSum) {
        for (int i = 0; i < numbers.length - 1; i++) {
            long sum = numbers[i];
            for (int j = i + 1; j < numbers.length || sum < searchableSum; j++) {
                sum += numbers[j];

                if (sum == searchableSum) {
                    return Arrays.copyOfRange(numbers, i, j + 1);
                }
            }
        }

        throw new ContiguousNumbersSumSetNotFoundException("Searchable sum: " + searchableSum);
    }

    long findMinMaxSum(Long[] numbers) {
        if (numbers.length < 2) {
            throw new ArrayTooSmallException(String.format("Expected length of at least 2, but actual - %s", numbers.length));
        }

        Long min = stream(numbers).min(Long::compare).get();
        Long max = stream(numbers).max(Long::compare).get();

        return min + max;
    }

}

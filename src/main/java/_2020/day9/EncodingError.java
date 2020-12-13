package _2020.day9;

import lombok.extern.java.Log;
import util.SolvableTask;

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

    public static void main(String[] args) {
        new EncodingError().solve();
    }

    @Override
    public void solve() {
        Long result = checkAllNumbers();
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

    EncodingError changePreamble(int preambleSize) {
        preamble = new LinkedList<>(asList(copyOf(numbers, preambleSize)));
        return this;
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

}

package _2021.day8;

import lombok.extern.java.Log;
import util.SolvableTask;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

@Log
public class SevenSegmentSearch implements SolvableTask {

    private final Map<Integer, Integer> DIGITS_TO_SEGMENTS_COUNT = new HashMap<>() {
        {
            put(1, 2);
            put(4, 4);
            put(7, 3);
            put(8, 7);
        }
    };

    public SevenSegmentSearch() {}

    public static void main(String[] args) {
        new SevenSegmentSearch().solve();
    }

    @Override
    public void solve() {
        String[] entries = getInputLines();
        long count = Arrays.stream(entries)
                .map(this::extractOutput)
                .map(String::strip)
                .flatMap(a -> Arrays.stream(a.split(" ")))
                .filter(outputLengthEquals(DIGITS_TO_SEGMENTS_COUNT.get(1))
                        .or(outputLengthEquals(DIGITS_TO_SEGMENTS_COUNT.get(4)))
                        .or(outputLengthEquals(DIGITS_TO_SEGMENTS_COUNT.get(7)))
                        .or(outputLengthEquals(DIGITS_TO_SEGMENTS_COUNT.get(8)))
                )
                .count();

        log.info(String.valueOf(count));
    }

    private Predicate<String> outputLengthEquals(int segmentsCount) {
        return output -> output.length() == segmentsCount;
    }

    private String extractOutput(String entry) {
        return entry.split("\\|")[1];
    }

}

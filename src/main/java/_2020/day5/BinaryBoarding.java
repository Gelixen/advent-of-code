package _2020.day5;

import lombok.extern.java.Log;
import util.SolvableTask;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toUnmodifiableSet;

@Log
public class BinaryBoarding implements SolvableTask {

    private static final char ROW_LOWER_HALF_CHAR = 'F';
    private static final int ROW_MAX = 127;
    private static final int ROW_MIN = 0;

    private static final char COLUMN_LOWER_HALF_CHAR = 'L';
    private static final int COLUMN_MAX = 7;
    private static final int COLUMN_MIN = 0;

    public static void main(String[] args) {
        new BinaryBoarding().solve();
    }

    @Override
    public void solve() {
        Set<Integer> seatIds = Arrays.stream(getInputLines())
                .map(BinaryBoarding::findSeat)
                .map(Seat::getId)
                .collect(toUnmodifiableSet());

        int freeSeatId = findFreeSeat(seatIds);

        log.info(String.valueOf(freeSeatId));
    }

    public static Seat findSeat(String code) {
        CharSequence rowCode = code.subSequence(0, 7);
        CharSequence columnCode = code.subSequence(7, 10);

        int row = findPlace(rowCode, ROW_LOWER_HALF_CHAR, ROW_MIN, ROW_MAX);
        int column = findPlace(columnCode, COLUMN_LOWER_HALF_CHAR, COLUMN_MIN, COLUMN_MAX);

        return new Seat(row, column);
    }

    public static int findPlace(CharSequence code, char lowerHalfChar, int min, int max) {
        for (int i = 0; i < code.length(); i++) {
            int halfDifference = (max - min) / 2;

            if (code.charAt(i) == lowerHalfChar) {
                max = min + halfDifference;
            } else {
                min = max - halfDifference;
            }
        }

        return max;
    }

    private int findFreeSeat(Set<Integer> seatIds) {
        int minId = seatIds.stream().mapToInt(i -> i).min().orElseThrow();
        int maxId = seatIds.stream().mapToInt(i -> i).max().orElseThrow();

        return IntStream.range(minId, maxId)
                .filter(id -> !seatIds.contains(id))
                .findFirst()
                .orElseThrow();
    }

}

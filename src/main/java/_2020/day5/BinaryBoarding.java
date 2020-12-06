package _2020.day5;

import lombok.extern.java.Log;
import util.SolvableTask;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.OptionalInt;

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

    @Override
    public String getPackageName() {
        return MethodHandles.lookup().lookupClass().getPackageName();
    }

    @Override
    public void solve() {
        OptionalInt highestSeatId = Arrays.stream(getInputLines())
                .map(BinaryBoarding::findSeat)
                .mapToInt(Seat::getId)
                .max();

        log.info(String.valueOf(highestSeatId.orElse(-1)));
    }

}

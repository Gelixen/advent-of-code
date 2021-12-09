package _2021.day7;

import lombok.extern.java.Log;
import util.SolvableTask;

import java.util.Arrays;

@Log
public class TheTreacheryOfWhales implements SolvableTask {

    public TheTreacheryOfWhales() {}

    public static void main(String[] args) {
        new TheTreacheryOfWhales().solve();
    }

    @Override
    public void solve() {
        int[] crabPositions = Arrays.stream(getInputLines(","))
                .mapToInt(Integer::parseInt)
                .toArray();

        int lowestFuel = Integer.MAX_VALUE;

        int max = Arrays.stream(crabPositions).max().getAsInt();

        for (int alignmentPosition = 0; alignmentPosition < max; alignmentPosition++) {
            int fuel = 0;

            for (int crabPosition : crabPositions) {
                int distanceToAlignmentPosition = Math.abs(crabPosition - alignmentPosition);
                fuel += countMoveCost(distanceToAlignmentPosition);
            }

            if (fuel < lowestFuel) {
                lowestFuel = fuel;
            }
        }

        log.info(String.valueOf(lowestFuel));
    }

    private int countMoveCost(int distance) {
        if (distance == 0) {
            return 0;
        }

        return distance + countMoveCost(--distance);
    }

}

package _2021.day1;

import lombok.extern.java.Log;
import util.SolvableTask;

import java.util.Arrays;

@Log
public class SonarSweep implements SolvableTask {

    public static void main(String[] args) {
        new SonarSweep().solve();
    }

    @Override
    public void solve() {
        int[] input = Arrays.stream(getInputLines())
                .mapToInt(Integer::parseInt)
                .toArray();

        int depthIncrements = findDepthIncrements(input);

        log.info(String.valueOf(depthIncrements));
    }

    private int findDepthIncrements(int[] input) {
        int lastDepth = input[0];
        int depthIncreaseCounter = 0;

        for (int depth : input) {
            if (depth > lastDepth) {
                depthIncreaseCounter++;
            }
            lastDepth = depth;
        }

        return depthIncreaseCounter;
    }
}

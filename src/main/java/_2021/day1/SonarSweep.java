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

    int findDepthIncrements(int[] input) {
        int lastDepthSum = input[0] + input[1] + input[2];
        int firstSumElementIndex = 0;
        int depthIncreaseCounter = 0;

        for (int i = 3; i < input.length; i++) {
            int depthSum = lastDepthSum - input[firstSumElementIndex] + input[i];
            if (depthSum > lastDepthSum) {
                depthIncreaseCounter++;
            }
            firstSumElementIndex++;
            lastDepthSum = depthSum;
        }

        return depthIncreaseCounter;
    }
}

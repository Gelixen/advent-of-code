package _2020.day10;

import lombok.extern.java.Log;
import util.SolvableTask;

import java.util.Arrays;

@Log
public class AdapterArray implements SolvableTask {

    private Integer[] adaptersVoltages = Arrays.stream(getInputLines()).map(Integer::parseInt).toArray(Integer[]::new);

    public static void main(String[] args) {
        new AdapterArray().solve();
    }

    @Override
    public void solve() {
        long result = countDistinctArrangements();

        log.info(String.valueOf(result));
    }

    public Long countDistinctArrangements() {
        adaptersVoltages = Arrays.copyOf(adaptersVoltages, adaptersVoltages.length + 1);
        adaptersVoltages[adaptersVoltages.length - 1] = 0;
        Arrays.sort(adaptersVoltages);

        Long[] possiblePaths = new Long[adaptersVoltages.length];
        Arrays.fill(possiblePaths, 0L);
        possiblePaths[0] = 1L;

        for (int i = 0; i < adaptersVoltages.length; i++) {
            Integer currentElement = adaptersVoltages[i];

            int maxDifferenceValue = currentElement + 3;
            for (int j = i + 1; j < i + 4 && j < adaptersVoltages.length; j++) {
                if (adaptersVoltages[j] <= maxDifferenceValue) {
                    possiblePaths[j] += possiblePaths[i];
                }
            }
        }

        return possiblePaths[adaptersVoltages.length - 1];
    }

}

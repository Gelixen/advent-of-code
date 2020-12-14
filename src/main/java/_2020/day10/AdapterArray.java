package _2020.day10;

import lombok.With;
import lombok.extern.java.Log;
import util.SolvableTask;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Log
public class AdapterArray implements SolvableTask {

    static final int DIFFERENCES_OF_1_JOLT_KEY = 1;
    static final int DIFFERENCES_OF_3_JOLT_KEY = 3;

    private final Map<Integer, Integer> voltageDifferencesCounter = new HashMap<>();

    @With
    private Integer[] adaptersVoltages = Arrays.stream(getInputLines()).map(Integer::parseInt).toArray(Integer[]::new);

    private AdapterArray(Integer[] adaptersVoltages) {
        this();
        this.adaptersVoltages = adaptersVoltages;
    }

    public AdapterArray() {
        voltageDifferencesCounter.put(DIFFERENCES_OF_1_JOLT_KEY, 0);
        voltageDifferencesCounter.put(DIFFERENCES_OF_3_JOLT_KEY, 0);
    }

    public static void main(String[] args) {
        new AdapterArray().solve();
    }

    @Override
    public void solve() {
        countAdaptersVoltagesDifferences();

        log.info(String.valueOf(voltageDifferencesCounter.values().stream().reduce((a, b) -> a * b)));
    }

    public Map<Integer, Integer> countAdaptersVoltagesDifferences() {
        Arrays.sort(adaptersVoltages);

        int previousVoltage = 0;

        for (int adaptersVoltage : adaptersVoltages) {
            int voltagesDifference = adaptersVoltage - previousVoltage;
            increaseVoltageDifferenceCounterFor(voltagesDifference);

            previousVoltage = adaptersVoltage;
        }

        devicesBuiltInAdapter();

        return voltageDifferencesCounter;
    }

    private void increaseVoltageDifferenceCounterFor(int voltageDifferenceCounterKey) {
        voltageDifferencesCounter.computeIfPresent(voltageDifferenceCounterKey, (key, value) -> value += 1);
    }

    private void devicesBuiltInAdapter() {
        increaseVoltageDifferenceCounterFor(3);
    }
}

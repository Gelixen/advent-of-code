package _2020.day10;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AdapterArrayTest {

    public static final int DIFFERENCES_OF_1_JOLT_KEY = 1;
    public static final int DIFFERENCES_OF_3_JOLT_KEY = 3;

    private static Stream<Arguments> countAdaptersVoltagesDifferences() {
        return Stream.of(
                Arguments.of(List.of(1), 1, 1),
                Arguments.of(List.of(3), 0, 2),
                Arguments.of(List.of(1, 2, 3), 3, 1),
                Arguments.of(List.of(3, 6, 9, 12), 0, 5),
                Arguments.of(List.of(16, 10, 15, 5, 1, 11, 7, 19, 6, 12, 4), 7, 5)
        );
    }

    @ParameterizedTest
    @MethodSource
    void countAdaptersVoltagesDifferences(List<Integer> input, int expected1JoltCount, int expected3JoltCount) {
        Integer[] arrayInput = input.toArray(Integer[]::new);
        AdapterArray adapterArray = new AdapterArray().withAdaptersVoltages(arrayInput);

        Map<Integer, Integer> result = adapterArray.countAdaptersVoltagesDifferences();

        assertTrue(result.containsKey(DIFFERENCES_OF_1_JOLT_KEY));
        assertEquals(expected1JoltCount, result.get(DIFFERENCES_OF_1_JOLT_KEY));

        assertTrue(result.containsKey(DIFFERENCES_OF_3_JOLT_KEY));
        assertEquals(expected3JoltCount, result.get(DIFFERENCES_OF_3_JOLT_KEY));
    }

    @Test
    void sample() {
        AdapterArray adapterArray = new AdapterArray();

        Map<Integer, Integer> result = adapterArray.countAdaptersVoltagesDifferences();

        assertTrue(result.containsKey(DIFFERENCES_OF_1_JOLT_KEY));
        assertEquals(22, result.get(DIFFERENCES_OF_1_JOLT_KEY));

        assertTrue(result.containsKey(DIFFERENCES_OF_3_JOLT_KEY));
        assertEquals(10, result.get(DIFFERENCES_OF_3_JOLT_KEY));
    }

}
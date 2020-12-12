package _2020.day8;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HandheldHaltingTest {

    private static Stream<Arguments> executeCommand() {
        return Stream.of(
                Arguments.of("nop +0", 0, 1),
                Arguments.of("acc +1", 1, 1),
                Arguments.of("jmp +4", 0, 4),
                Arguments.of("acc +3", 3, 1),
                Arguments.of("jmp -3", 0, -3),
                Arguments.of("acc -99", -99, 1),
                Arguments.of("acc +1", 1, 1),
                Arguments.of("jmp -4", 0, -4),
                Arguments.of("acc +6", 6, 1)
        );
    }

    @ParameterizedTest
    @MethodSource
    void executeCommand(String command, int expectedAccumulatorValue, int expectedNextCommandIndex) {
        HandheldHalting handheldHalting = new HandheldHalting();

        handheldHalting.executeCommand(command, true);

        assertEquals(expectedAccumulatorValue, handheldHalting.getAccumulatorValue());
        assertEquals(expectedNextCommandIndex, handheldHalting.getNextCommandIndex());
    }

    @Test
    void executeCommands_sample_withoutSwitching() {
        HandheldHalting handheldHalting = new HandheldHalting();

        handheldHalting.executeCommands(true);

        assertFalse(handheldHalting.isEndReached());
        assertEquals(5, handheldHalting.getAccumulatorValue());
        assertEquals(1, handheldHalting.getNextCommandIndex());
    }

    @Test
    void executeCommands_sample_withSwitching() {
        HandheldHalting handheldHalting = new HandheldHalting();

        handheldHalting.executeCommands(false);

        assertTrue(handheldHalting.isEndReached());
        assertEquals(8, handheldHalting.getAccumulatorValue());
        assertEquals(9, handheldHalting.getNextCommandIndex());
    }
}
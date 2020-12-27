package _2020.day12;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static _2020.day12.Rotation.EAST;
import static _2020.day12.Rotation.SOUTH;
import static _2020.day12.Rotation.WEST;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RainRiskTest {

    private static final Position DEFAULT_POSITION = new Position(0, 0, EAST);

    private static Stream<Arguments> navigate() {
        return Stream.of(
                Arguments.of("N3", 0, 3, EAST),
                Arguments.of("S4", 0, -4, EAST),
                Arguments.of("E8", 8, 0, EAST),
                Arguments.of("W1", -1, 0, EAST),
                Arguments.of("L180", 0, 0, WEST),
                Arguments.of("R90", 0, 0, SOUTH),
                Arguments.of("F11", 11, 0, EAST)
        );
    }

    @ParameterizedTest(name = "\"{0}\"instruction navigate to ({1}, {2}) while facing {3}")
    @MethodSource
    void navigate(String instruction, int expectedX, int expectedY, Rotation expectedRotation) {
        RainRisk rainRisk = new RainRisk(DEFAULT_POSITION);

        Position result = rainRisk.navigate(instruction);

        assertEquals(expectedX, result.getX());
        assertEquals(expectedY, result.getY());
        assertEquals(expectedRotation, result.getRotation());
    }

    @Test
    void solve_sample() {
        RainRisk rainRisk = new RainRisk(DEFAULT_POSITION);

        rainRisk.solve();
        int manhattanDistance = rainRisk.getManhattanDistance();

        assertEquals(25, manhattanDistance);
    }

}

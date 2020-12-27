package _2020.day12;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static _2020.day12.Rotation.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RotationTest {

    private static Stream<Arguments> rotate() {
        return Stream.of(
                Arguments.of(EAST, 90, SOUTH),
                Arguments.of(EAST, 180, WEST),
                Arguments.of(EAST, 270, NORTH),
                Arguments.of(EAST, 360, EAST),
                Arguments.of(EAST, 720, EAST),
                Arguments.of(EAST, -90, NORTH),
                Arguments.of(EAST, -180, WEST),
                Arguments.of(EAST, -270, SOUTH),
                Arguments.of(EAST, -360, EAST),
                Arguments.of(EAST, -720, EAST),
                Arguments.of(SOUTH, 90, WEST),
                Arguments.of(SOUTH, 180, NORTH),
                Arguments.of(SOUTH, 270, EAST),
                Arguments.of(NORTH, 90, EAST),
                Arguments.of(NORTH, 180, SOUTH),
                Arguments.of(NORTH, 270, WEST),
                Arguments.of(WEST, 90, NORTH),
                Arguments.of(WEST, 180, EAST),
                Arguments.of(WEST, 270, SOUTH)
        );
    }

    @ParameterizedTest(name = "{0} rotated {1} degrees -> {2}")
    @MethodSource
    void rotate(Rotation initialRotation, int degree, Rotation expectedRotation) {
        assertEquals(expectedRotation, initialRotation.rotate(degree));
    }
}
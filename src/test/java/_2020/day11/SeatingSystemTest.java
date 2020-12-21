package _2020.day11;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SeatingSystemTest {

    private final char[][] SAMPLE_MATRIX_RESULT = {
            {'#', '.', '#', 'L', '.', 'L', '#', '.', '#', '#'},
            {'#', 'L', 'L', 'L', '#', 'L', 'L', '.', 'L', '#'},
            {'L', '.', '#', '.', 'L', '.', '.', '#', '.', '.'},
            {'#', 'L', '#', '#', '.', '#', '#', '.', 'L', '#'},
            {'#', '.', '#', 'L', '.', 'L', 'L', '.', 'L', 'L'},
            {'#', '.', '#', 'L', '#', 'L', '#', '.', '#', '#'},
            {'.', '.', 'L', '.', 'L', '.', '.', '.', '.', '.'},
            {'#', 'L', '#', 'L', '#', '#', 'L', '#', 'L', '#'},
            {'#', '.', 'L', 'L', 'L', 'L', 'L', 'L', '.', 'L'},
            {'#', '.', '#', 'L', '#', 'L', '#', '.', '#', '#'}
    };

    @Test
    void calculateNewState_beforeFirstRound() {
        SeatingSystem seatingSystem = new SeatingSystem();

        char result = seatingSystem.calculateNewState(0, 2);

        assertEquals('#', result);
    }

    @Test
    void calculateNewState_afterFirstRound() {
        SeatingSystem seatingSystem = new SeatingSystem();

        seatingSystem.hasAnyStateChangedIterating();
        char result = seatingSystem.calculateNewState(0, 2);

        assertEquals('L', result);
    }

    @Test
    void calculateNewState_afterSecondRound() {
        SeatingSystem seatingSystem = new SeatingSystem();

        seatingSystem.hasAnyStateChangedIterating();
        seatingSystem.hasAnyStateChangedIterating();
        char result = seatingSystem.calculateNewState(0, 2);

        assertEquals('#', result);
    }

    @Test
    void getOccupiedSeatsCount_beforeFirstRound() {
        SeatingSystem seatingSystem = new SeatingSystem();

        int occupiedSeatsCount = seatingSystem.getOccupiedSeatsCount();

        assertEquals(0, occupiedSeatsCount);
    }

    @Test
    void getOccupiedSeatsCount_afterFirstRound() {
        SeatingSystem seatingSystem = new SeatingSystem();

        int occupiedSeatsCount = seatingSystem.getOccupiedSeatsCount();

        assertEquals(0, occupiedSeatsCount);
    }

    @Test
    void iterateRoundsTillNoChanges_sample() {
        SeatingSystem seatingSystem = new SeatingSystem();

        char[][] finalMatrix = seatingSystem.iterateRoundsTillNoChanges();
        int occupiedSeatsCount = seatingSystem.getOccupiedSeatsCount();

        assertArrayEquals(SAMPLE_MATRIX_RESULT, finalMatrix);
        assertEquals(37, occupiedSeatsCount);
    }

}
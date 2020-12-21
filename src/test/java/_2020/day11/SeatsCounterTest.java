package _2020.day11;

import org.junit.jupiter.api.Test;

import static _2020.day11.ConversionUtil.stringToCharMatrix;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SeatsCounterTest {

    private final String SAMPLE_MATRIX_TEXT = """
            .......#.
            ...#.....
            .#.......
            .........
            ..#L....#
            ....#....
            .........
            #........
            ...#.....
            """;
    private final char[][] SAMPLE_MATRIX = stringToCharMatrix(SAMPLE_MATRIX_TEXT);

    @Test
    void countAdjacentOccupiedSeats_sample_middleEmptySeat_twoAdjacentSeatsOccupied() {
        SeatsCounter seatsCounter = new SeatsCounter().withMatrix(SAMPLE_MATRIX);

        int occupiedSeats = seatsCounter.countAdjacentOccupiedSeats(4, 3);

        assertEquals(2, occupiedSeats);
    }

    @Test
    void countAdjacentOccupiedSeats_sample_uppermostOccupiedSeat_noneAdjacentSeatsOccupied() {
        SeatsCounter seatsCounter = new SeatsCounter().withMatrix(SAMPLE_MATRIX);

        int occupiedSeats = seatsCounter.countAdjacentOccupiedSeats(0, 7);

        assertEquals(0, occupiedSeats);
    }

    @Test
    void countAdjacentOccupiedSeats_sample_bottommostFirstPosition_singleAdjacentSeatOccupied() {
        SeatsCounter seatsCounter = new SeatsCounter().withMatrix(SAMPLE_MATRIX);

        int occupiedSeats = seatsCounter.countAdjacentOccupiedSeats(8, 0);

        assertEquals(1, occupiedSeats);
    }

}
package _2020.day11;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SeatingSystemTest {

    private final char[][] SAMPLE_MATRIX_RESULT = ConversionUtil.stringToCharMatrix("""
            #.L#.L#.L#
            #LLLLLL.LL
            L.L.L..#..
            ##L#.#L.L#
            L.L#.LL.L#
            #.LLLL#.LL
            ..#.L.....
            LLL###LLL#
            #.LLLLL#.L
            #.L#LL#.L#
            """);

    @ParameterizedTest(name = "After {0} iterations has state changed - {1}")
    @CsvSource(value = {
            "1:true",
            "2:true",
            "3:true",
            "4:true",
            "5:true",
            "6:true",
            "7:false",
            "8:false",
            "9:false"
    }, delimiter = ':')
    void calculateNewState_beforeFirstRound(int iterationsCount, boolean expectedStateChange) {
        SeatingSystem seatingSystem = new SeatingSystem();
        boolean hasStateChanged = false;

        for (int i = 0; i < iterationsCount; i++) {
            hasStateChanged = seatingSystem.hasAnyStateChangedIterating();
        }

        assertEquals(expectedStateChange, hasStateChanged);
    }

    @ParameterizedTest(name = "After {0} iteration there should be {1} occupied seats")
    @CsvSource(value = {
            "0:0",
            "1:71",
            "2:7",
            "3:53",
            "4:18",
            "5:31",
            "6:26"
    }, delimiter = ':')
    void getOccupiedSeatsCount_beforeFirstRound(int iterationsCount, int expectedSeatsCount) {
        SeatingSystem seatingSystem = new SeatingSystem();

        for (int i = 0; i < iterationsCount; i++) {
            seatingSystem.hasAnyStateChangedIterating();
        }
        int occupiedSeatsCount = seatingSystem.getOccupiedSeatsCount();

        assertEquals(expectedSeatsCount, occupiedSeatsCount);
    }

    @Test
    void iterateRoundsTillNoChanges_sample() {
        SeatingSystem seatingSystem = new SeatingSystem();

        char[][] finalMatrix = seatingSystem.iterateRoundsTillNoChanges();
        int occupiedSeatsCount = seatingSystem.getOccupiedSeatsCount();

        assertArrayEquals(SAMPLE_MATRIX_RESULT, finalMatrix);
        assertEquals(26, occupiedSeatsCount);
    }

}
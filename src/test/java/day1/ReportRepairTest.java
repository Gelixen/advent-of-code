package day1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReportRepairTest {

    @Test
    void findSumPair_pairWithZero() {
        int[] input = new int[]{5, 0, 0};

        Triple result = ReportRepair.findSumPair(input, 5);

        assertEquals(5, result.getFirst());
        assertEquals(0, result.getSecond());
        assertEquals(0, result.getThird());
    }

    @Test
    void findSumPair_matchingPair() {
        int[] input = new int[]{3, 3, 3};

        Triple result = ReportRepair.findSumPair(input, 9);

        assertEquals(3, result.getFirst());
        assertEquals(3, result.getSecond());
        assertEquals(3, result.getThird());
    }

    @Test
    void findSumPair_inputOfThree() {
        int[] input = new int[]{2, 0, 5};

        Triple result = ReportRepair.findSumPair(input, 7);

        assertEquals(2, result.getFirst());
        assertEquals(0, result.getSecond());
        assertEquals(5, result.getThird());
    }

    @Test
    void findSumPair_inputOfFive() {
        int[] input = new int[]{2, 1, 4, 3, 5};

        Triple result = ReportRepair.findSumPair(input, 9);

        assertEquals(2, result.getFirst());
        assertEquals(4, result.getSecond());
        assertEquals(3, result.getThird());
    }

    @Test
    void findSumPair_pairNotFound_throwException() {
        int[] input = new int[]{2, 5};

        assertThrows(RuntimeException.class, () -> ReportRepair.findSumPair(input, 9));
    }

    @Test
    void multiplyZeroByTwoByThree_Zero() {
        Triple triple = new Triple(0, 2, 3);

        int result = ReportRepair.multiply(triple);

        assertEquals(0, result);
    }

    @Test
    void multiplyOneByTwoByThree_Six() {
        Triple triple = new Triple(1, 2, 3);

        int result = ReportRepair.multiply(triple);

        assertEquals(6, result);
    }

    @Test
    void multiplyTwoByFourByTen_Eighty() {
        Triple triple = new Triple(2, 4, 10);

        int result = ReportRepair.multiply(triple);

        assertEquals(80, result);
    }
}
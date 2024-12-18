package _2020.day1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ReportRepairTest {

    @Test
    void findSumPair_pairWithZero() {
        int[] input = new int[]{5, 0, 0};

        Triple result = ReportRepair.findSumPair(input, 5);

        assertEquals(5, result.first());
        assertEquals(0, result.second());
        assertEquals(0, result.third());
    }

    @Test
    void findSumPair_matchingPair() {
        int[] input = new int[]{3, 3, 3};

        Triple result = ReportRepair.findSumPair(input, 9);

        assertEquals(3, result.first());
        assertEquals(3, result.second());
        assertEquals(3, result.third());
    }

    @Test
    void findSumPair_inputOfThree() {
        int[] input = new int[]{2, 0, 5};

        Triple result = ReportRepair.findSumPair(input, 7);

        assertEquals(2, result.first());
        assertEquals(0, result.second());
        assertEquals(5, result.third());
    }

    @Test
    void findSumPair_inputOfFive() {
        int[] input = new int[]{2, 1, 4, 3, 5};

        Triple result = ReportRepair.findSumPair(input, 9);

        assertEquals(2, result.first());
        assertEquals(4, result.second());
        assertEquals(3, result.third());
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
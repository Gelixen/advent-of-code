package day1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReportRepairTest {

    @Test
    void findSumPair_pairWithZero() {
        int[] input = new int[]{5, 0};

        Pair<Integer, Integer> result = ReportRepair.findSumPair(input, 5);

        assertEquals(5, result.getFirst());
        assertEquals(0, result.getSecond());
    }

    @Test
    void findSumPair_matchingPair() {
        int[] input = new int[]{3, 3};

        Pair<Integer, Integer> result = ReportRepair.findSumPair(input, 6);

        assertEquals(3, result.getFirst());
        assertEquals(3, result.getSecond());
    }

    @Test
    void findSumPair_inputOfThree() {
        int[] input = new int[]{2, 0, 5};

        Pair<Integer, Integer> result = ReportRepair.findSumPair(input, 5);

        assertEquals(0, result.getFirst());
        assertEquals(5, result.getSecond());
    }

    @Test
    void findSumPair_inputOfFive() {
        int[] input = new int[]{2, 1, 4, 3, 5};

        Pair<Integer, Integer> result = ReportRepair.findSumPair(input, 9);

        assertEquals(4, result.getFirst());
        assertEquals(5, result.getSecond());
    }

    @Test
    void findSumPair_pairNotFound_throwException() {
        int[] input = new int[]{2, 5};

        assertThrows(RuntimeException.class, () -> ReportRepair.findSumPair(input, 9));
    }

    @Test
    void multiplyTwoByZero_Zero() {
        Pair<Integer, Integer> pair = new Pair<>(0, 2);

        int result = ReportRepair.multiply(pair);

        assertEquals(0, result);
    }

    @Test
    void multiplyTwoByOne_Two() {
        Pair<Integer, Integer> pair = new Pair<>(1, 2);

        int result = ReportRepair.multiply(pair);

        assertEquals(2, result);
    }

    @Test
    void multiplyFiveByTen_Fifty() {
        Pair<Integer, Integer> pair = new Pair<>(5, 10);

        int result = ReportRepair.multiply(pair);

        assertEquals(50, result);
    }
}
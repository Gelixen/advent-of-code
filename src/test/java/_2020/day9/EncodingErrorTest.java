package _2020.day9;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EncodingErrorTest {

    @Test
    void sumPairExists_firstNumberAsInput_pairNotFound() {
        EncodingError encodingError = new EncodingError().changePreamble(2);

        boolean pairExists = encodingError.sumPairExists(35);

        assertFalse(pairExists);
    }

    @Test
    void sumPairExists_secondNumberAsInput_pairNotFound() {
        EncodingError encodingError = new EncodingError().changePreamble(2);

        boolean pairExists = encodingError.sumPairExists(20);

        assertFalse(pairExists);
    }

    @Test
    void sumPairExists_randomNumberAsInput_pairNotFound() {
        EncodingError encodingError = new EncodingError().changePreamble(2);

        boolean pairExists = encodingError.sumPairExists(10);

        assertFalse(pairExists);
    }

    @Test
    void sumPairExists_firstTwoNumbersSumAsInput_pairExists() {
        EncodingError encodingError = new EncodingError().changePreamble(2);

        boolean pairExists = encodingError.sumPairExists(55);

        assertTrue(pairExists);
    }

    @Test
    void sumPairExists_sampleFirstCase_pairExists() {
        EncodingError encodingError = new EncodingError().changePreamble(5);

        boolean pairExists = encodingError.sumPairExists(40);

        assertTrue(pairExists);
    }

    @Test
    void checkAllNumbers_sample() {
        EncodingError encodingError = new EncodingError().changePreamble(5);

        Long result = encodingError.checkAllNumbers();

        assertEquals(127, result);
    }

    @Test
    void findContiguousNumbersSumSet_inputAsFirstElement_shouldIgnoreSumOfSingleElement() {
        Long[] expectedResult = {20L, 15L};
        EncodingError encodingError = new EncodingError().changePreamble(5);

        Long[] result = encodingError.findContiguousNumbersSumSet(35L);

        assertArrayEquals(expectedResult, result);
    }

    @Test
    void findContiguousNumbersSumSet_inputAsLastThreeNumbersSum_shouldReturnLastThreeNumbers() {
        Long[] expectedResult = {277L, 309L, 576L};
        EncodingError encodingError = new EncodingError().changePreamble(5);

        Long[] result = encodingError.findContiguousNumbersSumSet(1162L);

        assertArrayEquals(expectedResult, result);
    }

    @Test
    void findContiguousNumbersSumSet_inputAsNonSummable_throwsContiguousNumbersSumSetNotFoundException() {
        EncodingError encodingError = new EncodingError().changePreamble(5);

        assertThrows(ContiguousNumbersSumSetNotFoundException.class, () ->
                encodingError.findContiguousNumbersSumSet(100L)
        );
    }

    @Test
    void findContiguousNumbersSumSet_sample() {
        Long[] expectedResult = {15L, 25L, 47L, 40L};
        EncodingError encodingError = new EncodingError().changePreamble(5);

        Long[] result = encodingError.findContiguousNumbersSumSet(127L);

        assertArrayEquals(expectedResult, result);
    }

    @Test
    void findMinMaxSum_EmptyArray_throwArrayTooSmallException() {
        Long[] input = {};
        EncodingError encodingError = new EncodingError().changePreamble(5);

        assertThrows(ArrayTooSmallException.class, () ->
                encodingError.findMinMaxSum(input)
        );
    }

    @Test
    void findMinMaxSum_arrayOfSingleElement_throwArrayTooSmallException() {
        Long[] input = {5L};
        EncodingError encodingError = new EncodingError().changePreamble(5);

        assertThrows(ArrayTooSmallException.class, () ->
                encodingError.findMinMaxSum(input)
        );
    }

    @Test
    void findMinMaxSum_aFewRandomNumbers_min1_max99_sum100() {
        Long[] input = {15L, 1L, 47L, 99L};
        EncodingError encodingError = new EncodingError().changePreamble(5);

        long result = encodingError.findMinMaxSum(input);

        assertEquals(100L, result);
    }

    @Test
    void findMinMaxSum_aFewRandomNumbers_min5_max81_sum86() {
        Long[] input = {5L, 14L, 49L, 81L, 9L};
        EncodingError encodingError = new EncodingError().changePreamble(5);

        long result = encodingError.findMinMaxSum(input);

        assertEquals(86L, result);
    }

    @Test
    void findMinMaxSum_sample() {
        Long[] input = {15L, 47L};
        EncodingError encodingError = new EncodingError().changePreamble(5);

        long result = encodingError.findMinMaxSum(input);

        assertEquals(62, result);
    }
}
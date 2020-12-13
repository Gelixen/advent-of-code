package _2020.day9;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
}
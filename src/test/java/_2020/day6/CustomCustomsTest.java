package _2020.day6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomCustomsTest {

    @Test
    void countDistinctGroupAnswers_singleAnswer_one() {
        long result = CustomCustoms.countDistinctGroupAnswers("b");

        assertEquals(1, result);
    }

    @Test
    void countDistinctGroupAnswers_oneLineDistinctAnswers_three() {
        long result = CustomCustoms.countDistinctGroupAnswers("abc");

        assertEquals(3, result);
    }

    @Test
    void countDistinctGroupAnswers_multilineDistinctAnswers_three() {
        long result = CustomCustoms.countDistinctGroupAnswers("""
                a
                b
                c"""
        );

        assertEquals(3, result);
    }

    @Test
    void countDistinctGroupAnswers_multilineNonDistinctAnswers_three() {
        long result = CustomCustoms.countDistinctGroupAnswers("""
                ab
                ac"""
        );

        assertEquals(3, result);
    }

    @Test
    void countDistinctGroupAnswers_multilineIdenticalAnswers_one() {
        long result = CustomCustoms.countDistinctGroupAnswers("""
                a
                a
                a
                a"""
        );

        assertEquals(1, result);
    }

    @Test
    void countDistinctGroupAnswers_multilineSemiIdenticalAnswers_six() {
        long result = CustomCustoms.countDistinctGroupAnswers("""
                abcx
                abcy
                abcz"""
        );

        assertEquals(6, result);
    }

    @Test
    void sumGroupsAnswers() {
        long result = CustomCustoms.sumGroupsAnswers(
                new String[]{
                        "abc",
                        """
                        a
                        b
                        c"""
                        ,
                        """
                        ab
                        ac"""
                        ,
                        """
                        a
                        a
                        a
                        a"""
                        ,
                        "b"
                }
        );

        assertEquals(11, result);
    }
}
package _2020.day6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomCustomsTest {

    @Test
    void countGroupAgreedAnswers_singleAnswer_one() {
        long result = CustomCustoms.countGroupAgreedAnswers("b");

        assertEquals(1, result);
    }

    @Test
    void countGroupAgreedAnswers_oneLineDistinctAnswers_three() {
        long result = CustomCustoms.countGroupAgreedAnswers("abc");

        assertEquals(3, result);
    }

    @Test
    void countGroupAgreedAnswers_multilineDistinctAnswers_three() {
        long result = CustomCustoms.countGroupAgreedAnswers("""
                a
                b
                c"""
        );

        assertEquals(0, result);
    }

    @Test
    void countGroupAgreedAnswers_multilineNonDistinctAnswers_three() {
        long result = CustomCustoms.countGroupAgreedAnswers("""
                ab
                ac"""
        );

        assertEquals(1, result);
    }

    @Test
    void countGroupAgreedAnswers_multilineIdenticalAnswers_one() {
        long result = CustomCustoms.countGroupAgreedAnswers("""
                a
                a
                a
                a"""
        );

        assertEquals(1, result);
    }

    @Test
    void countGroupAgreedAnswers_multilineSemiIdenticalAnswers_six() {
        long result = CustomCustoms.countGroupAgreedAnswers("""
                abcx
                abcy
                abcz"""
        );

        assertEquals(3, result);
    }

    @Test
    void sumGroupsAgreedAnswers() {
        long result = CustomCustoms.sumGroupsAgreedAnswers(
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

        assertEquals(6, result);
    }
}
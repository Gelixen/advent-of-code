package _2020.day2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PasswordPolicyTest {

    @Test
    void isValid_aAtFirstPositionOnly_valid() {
        runAndAssertIsValid("abcde", 'a', 0, 2, true);
    }

    @Test
    void isValid_zAtSecondPositionOnly_valid() {
        runAndAssertIsValid("aaaz", 'z', 1, 3, true);
    }

    @Test
    void isValid_bAtNeitherPositions_invalid() {
        runAndAssertIsValid("cdefg", 'b', 0, 2, false);
    }

    @Test
    void isValid_cAtBothPositions_invalid() {
        runAndAssertIsValid("ccccccccc", 'c', 1, 8, false);
    }

    @Test
    void isValid_samePosition_cPresentTwice_invalid() {
        runAndAssertIsValid("ccc", 'c', 1, 1, false);
    }

    @Test
    void isValid_samePosition_cMissing_invalid() {
        runAndAssertIsValid("aaa", 'c', 0, 0, false);
    }

    private void runAndAssertIsValid(String password, char letter, int firstPosition, int secondPosition, boolean expectedResult) {
        PasswordPolicy passwordPolicy = new PasswordPolicy(letter, firstPosition, secondPosition);

        boolean result = passwordPolicy.isValid(password);

        assertEquals(expectedResult, result);
    }
}
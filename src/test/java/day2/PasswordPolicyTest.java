package day2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordPolicyTest {

    @Test
    void isValid_aPresent_valid() {
        runAndAssertIsValid("abcde", 'a', 1, 3, true);
    }

    @Test
    void isValid_bMissing_invalid() {
        runAndAssertIsValid("cdefg", 'b', 1, 3, false);
    }

    @Test
    void isValid_cPresent_valid() {
        runAndAssertIsValid("ccccccccc", 'c', 2, 9, true);
    }

    @Test
    void isValid_cTooMany_invalid() {
        runAndAssertIsValid("cc", 'c', 0, 1, false);
    }

    @Test
    void isValid_cTooFew_invalid() {
        runAndAssertIsValid("cc", 'c', 3, 4, false);
    }

    @Test
    void isValid_cPresent_invalid() {
        runAndAssertIsValid("c", 'c', 0, 0, false);
    }

    private void runAndAssertIsValid(String password, char letter, int leastOccurrence, int mostOccurrence, boolean expectedResult) {
        PasswordPolicy passwordPolicy = new PasswordPolicy(letter, leastOccurrence, mostOccurrence);

        boolean result = passwordPolicy.isValid(password);

        assertEquals(expectedResult, result);
    }
}
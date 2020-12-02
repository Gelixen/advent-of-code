package _2020.day2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PasswordPhilosophyTest {

    @Test
    void toPasswordWithPolicy() {
        runAndAssertToPasswordWithPolicy("abcde", 'a', 1, 3);
    }

    @Test
    void toPasswordWithPolicy2() {
        runAndAssertToPasswordWithPolicy("cdefg", 'b', 1, 3);
    }

    @Test
    void toPasswordWithPolicy3() {
        runAndAssertToPasswordWithPolicy("ccccccccc", 'c', 2, 9);
    }

    private void runAndAssertToPasswordWithPolicy(String password, char letter, int firstPosition, int secondPosition) {
        String input = String.format("%d-%d %c: %s", firstPosition, secondPosition, letter, password);

        PasswordWithPolicy passwordWithPolicy = PasswordPhilosophy.toPasswordWithPolicy(input);

        assertEquals(password, passwordWithPolicy.getPassword());
        PasswordPolicy passwordPolicy = passwordWithPolicy.getPasswordPolicy();
        assertEquals(letter, passwordPolicy.getLetter());
        assertEquals(firstPosition - 1, passwordPolicy.getFirstPosition());
        assertEquals(secondPosition - 1, passwordPolicy.getSecondPosition());
    }
}
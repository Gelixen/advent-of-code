package _2020.day2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

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

        assertEquals(password, passwordWithPolicy.password());
        PasswordPolicy passwordPolicy = passwordWithPolicy.passwordPolicy();
        assertEquals(letter, passwordPolicy.letter());
        assertEquals(firstPosition - 1, passwordPolicy.firstPosition());
        assertEquals(secondPosition - 1, passwordPolicy.secondPosition());
    }
}
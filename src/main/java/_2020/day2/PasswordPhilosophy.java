package _2020.day2;

import lombok.extern.java.Log;
import util.SolvableTask;

import java.util.Arrays;

@Log
public class PasswordPhilosophy implements SolvableTask {

    public static void main(String[] args) {
        new PasswordPhilosophy().solve();
    }

    @Override
    public void solve() {
        long result = Arrays.stream(getInputLines())
                .map(PasswordPhilosophy::toPasswordWithPolicy)
                .filter(PasswordWithPolicy::isValid)
                .count();

        log.info(String.valueOf(result));
    }

    public static PasswordWithPolicy toPasswordWithPolicy(String line) {
        String[] parts = line.split("-| |: ");

        String password = parts[3];
        char letter = parts[2].charAt(0);
        int firstPosition = Integer.parseInt(parts[0]) - 1;
        int secondPosition = Integer.parseInt(parts[1]) - 1;

        PasswordPolicy passwordPolicy = new PasswordPolicy(letter, firstPosition, secondPosition);

        return new PasswordWithPolicy(password, passwordPolicy);
    }

}

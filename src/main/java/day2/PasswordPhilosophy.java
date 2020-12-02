package day2;

import util.FileReader;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;

public class PasswordPhilosophy {

    public static void main(String[] args) {
        long result = Arrays.stream(getInput().split("\n"))
                .map(PasswordPhilosophy::toPasswordWithPolicy)
                .filter(PasswordWithPolicy::isValid)
                .count();

        System.out.println(result);
    }

    private static String getInput() {
        String day = MethodHandles.lookup().lookupClass().getPackageName();
        return FileReader.readInput(day);
    }

    public static PasswordWithPolicy toPasswordWithPolicy(String line) {
        String[] parts = line.split("-| |: ");

        String password = parts[3];
        char letter = parts[2].charAt(0);
        int leastOccurrence = Integer.parseInt(parts[0]);
        int mostOccurrence = Integer.parseInt(parts[1]);

        PasswordPolicy passwordPolicy = new PasswordPolicy(letter, leastOccurrence, mostOccurrence);

        return new PasswordWithPolicy(password, passwordPolicy);
    }

}

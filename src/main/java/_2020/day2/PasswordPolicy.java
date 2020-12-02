package _2020.day2;

import lombok.Value;

@Value
public class PasswordPolicy {
    private char letter;
    private int firstPosition;
    private int secondPosition;

    public boolean isValid(String password) {
        return password.charAt(firstPosition) == letter
                ^ password.charAt(secondPosition) == letter;
    }
}
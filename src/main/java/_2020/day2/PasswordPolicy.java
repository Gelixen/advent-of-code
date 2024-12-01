package _2020.day2;

public record PasswordPolicy(char letter, int firstPosition, int secondPosition) {

    public boolean isValid(String password) {
        return password.charAt(firstPosition) == letter
                ^ password.charAt(secondPosition) == letter;
    }
}
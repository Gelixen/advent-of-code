package day2;

public class PasswordPolicy {
    private char letter;
    private int firstPosition;
    private int secondPosition;

    public PasswordPolicy(char letter, int firstPosition, int secondPosition) {
        this.letter = letter;
        this.firstPosition = firstPosition;
        this.secondPosition = secondPosition;
    }

    public char getLetter() {
        return letter;
    }

    public int getFirstPosition() {
        return firstPosition;
    }

    public int getSecondPosition() {
        return secondPosition;
    }

    public boolean isValid(String password) {
        return password.charAt(firstPosition) == letter
                ^ password.charAt(secondPosition) == letter;
    }
}
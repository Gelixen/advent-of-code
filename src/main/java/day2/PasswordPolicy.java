package day2;

public class PasswordPolicy {
    private char letter;
    private int leastOccurrence;
    private int mostOccurrence;

    public PasswordPolicy(char letter, int leastOccurrence, int mostOccurrence) {
        this.letter = letter;
        this.leastOccurrence = leastOccurrence;
        this.mostOccurrence = mostOccurrence;
    }

    public char getLetter() {
        return letter;
    }

    public int getLeastOccurrence() {
        return leastOccurrence;
    }

    public int getMostOccurrence() {
        return mostOccurrence;
    }

    public boolean isValid(String password) {
        long count = password.codePoints().filter(l -> l == letter).count();
        return count >= leastOccurrence
                && count <= mostOccurrence;
    }
}
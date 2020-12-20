package _2020.day11;

public class PositionStateNotFoundException extends RuntimeException {
    public PositionStateNotFoundException(char symbol) {
        super(String.format("Could not find state with symbol '%s'", symbol));
    }
}

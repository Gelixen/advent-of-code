package _2022.day2;

public class HandShapeNotFoundException extends RuntimeException {
    public HandShapeNotFoundException(char symbol) {
        super(String.format("Could not find hand shape from symbol '%s'", symbol));
    }
}

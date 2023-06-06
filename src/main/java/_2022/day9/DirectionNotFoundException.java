package _2022.day9;

public class DirectionNotFoundException extends RuntimeException {

    public DirectionNotFoundException(char symbol) {
        super(String.format("Could not find direction from symbol '%s'", symbol));
    }
}
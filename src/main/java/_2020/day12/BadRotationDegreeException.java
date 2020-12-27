package _2020.day12;

public class BadRotationDegreeException extends RuntimeException {
    public BadRotationDegreeException(int degree) {
        super(String.format("Could not find Rotation with degree '%s'", degree));
    }
}

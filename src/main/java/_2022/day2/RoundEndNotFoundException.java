package _2022.day2;

public class RoundEndNotFoundException extends RuntimeException {
    public RoundEndNotFoundException(char symbol) {
        super(String.format("Could not find round end from symbol '%s'", symbol));
    }
}

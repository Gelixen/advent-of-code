package _2021.day2;

import lombok.Value;

@Value
public class PositionParameters {
    int horizontalPosition;
    int depth;

    public PositionParameters increaseHorizontalPosition(int step) {
        return new PositionParameters(horizontalPosition + step, depth);
    }

    public PositionParameters increaseDepth(int step) {
        return new PositionParameters(horizontalPosition, depth + step);
    }

    public PositionParameters decreaseDepth(int step) {
        return new PositionParameters(horizontalPosition, depth - step);
    }
}

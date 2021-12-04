package _2021.day2;

import lombok.Value;

@Value
public class PositionParameters {
    int horizontalPosition;
    int depth;
    int aim;

    public PositionParameters increaseHorizontalPosition(int step) {
        return new PositionParameters(horizontalPosition + step, depth, aim);
    }

    public PositionParameters increaseAim(int step) {
        return new PositionParameters(horizontalPosition, depth, aim + step);
    }

    public PositionParameters increaseDepth(int step) {
        return new PositionParameters(horizontalPosition, depth + step, aim);
    }

    public PositionParameters decreaseAim(int step) {
        return new PositionParameters(horizontalPosition, depth, aim - step);
    }
}

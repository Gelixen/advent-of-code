package _2021.day2;

public enum Command {
    FORWARD {
        @Override
        PositionParameters execute(PositionParameters position, int step) {
            return position.increaseHorizontalPosition(step);
        }
    },
    UP {
        @Override
        PositionParameters execute(PositionParameters position, int step) {
            return position.decreaseDepth(step);
        }
    },
    DOWN {
        @Override
        PositionParameters execute(PositionParameters position, int step) {
            return position.increaseDepth(step);
        }
    };

    abstract PositionParameters execute(PositionParameters positionParameters, int step);
}

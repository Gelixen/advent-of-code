package _2021.day2;

public enum Command {
    FORWARD {
        @Override
        PositionParameters execute(PositionParameters position, int step) {
            int depthStep = position.aim() * step;
            return position.increaseHorizontalPosition(step)
                    .increaseDepth(depthStep);
        }
    },
    UP {
        @Override
        PositionParameters execute(PositionParameters position, int step) {
            return position.decreaseAim(step);
        }
    },
    DOWN {
        @Override
        PositionParameters execute(PositionParameters position, int step) {
            return position.increaseAim(step);
        }
    };

    abstract PositionParameters execute(PositionParameters positionParameters, int step);
}

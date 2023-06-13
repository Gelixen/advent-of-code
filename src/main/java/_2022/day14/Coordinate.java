package _2022.day14;

import static _2022.day14.CoordinateType.EMPTY;

public record Coordinate(CoordinateType type, int x, int y) {
    public Coordinate getCoordinateBelow(CoordinateType type) {
        return new Coordinate(type, x, y + 1);
    }

    public Coordinate getCoordinateBelow() {
        return new Coordinate(EMPTY, x, y + 1);
    }

    public Coordinate getCoordinateBelowRight() {
        return new Coordinate(type, x - 1, y + 1);
    }

    public Coordinate getCoordinateBelowLeft() {
        return new Coordinate(type, x + 1, y + 1);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof Coordinate coordinate)) {
            return false;
        }
        if (coordinate.x == this.x && coordinate.y == this.y) {
            return true;
        }
        return false;
    }
}

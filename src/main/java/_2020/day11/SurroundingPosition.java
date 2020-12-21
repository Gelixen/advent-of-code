package _2020.day11;

import lombok.Getter;

@Getter
public enum SurroundingPosition {
    UP_LEFT(-1, -1),
    UP(-1, 0),
    UP_RIGHT(-1, 1),
    LEFT(0, -1),
    RIGHT(0, 1),
    DOWN_LEFT(1, -1),
    DOWN(1, 0),
    DOWN_RIGHT(1, 1);

    private final int row;
    private final int column;

    SurroundingPosition(int row, int column) {
        this.row = row;
        this.column = column;
    }

}

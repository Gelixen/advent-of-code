package _2020.day12;

import lombok.Value;

import static _2020.day12.Rotation.*;

@Value
public class Position {
    int x;
    int y;
    Rotation rotation;

    public Position moveNorth(int value) {
        return new Position(x, y + value, rotation);
    }

    public Position moveSouth(int value) {
        return new Position(x, y - value, rotation);
    }

    public Position moveEast(int value) {
        return new Position(x + value, y, rotation);
    }

    public Position moveWest(int value) {
        return new Position(x - value, y, rotation);
    }

    public Position rotateLeftWise(int value) {
        return new Position(x, y, rotation.rotate(-value));
    }

    public Position rotateRightWise(int value) {
        return new Position(x, y, rotation.rotate(value));
    }

    public Position moveForward(int value) {

        if (rotation == NORTH) {
            return moveNorth(value);
        }
        if (rotation == SOUTH) {
            return moveSouth(value);
        }
        if (rotation == EAST) {
            return moveEast(value);
        }
        if (rotation == WEST) {
            return moveWest(value);
        }
        //
        return new Position(x, y, rotation);
    }
}

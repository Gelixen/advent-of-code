package _2020.day12;

import lombok.extern.java.Log;
import util.SolvableTask;

import java.util.Arrays;

@Log
public class RainRisk implements SolvableTask {

    private Position position;

    public RainRisk(Position position) {
        this.position = position;
    }

    public static void main(String[] args) {
        new RainRisk(new Position(0, 0, Rotation.EAST)).solve();
    }

    @Override
    public void solve() {
        Arrays.stream(getInputLines())
                .forEach(instruction -> position = navigate(instruction));

        log.info(String.valueOf(getManhattanDistance()));
    }

    public int getManhattanDistance() {
        int absX = Math.abs(position.getX());
        int absY = Math.abs(position.getY());

        return absX + absY;
    }

    public Position navigate(String instruction) {
        int value = Integer.parseInt(instruction.substring(1));

        char action = instruction.charAt(0);
        if (action == 'N') {
            return position.moveNorth(value);
        }
        if (action == 'S') {
            return position.moveSouth(value);
        }
        if (action == 'E') {
            return position.moveEast(value);
        }
        if (action == 'W') {
            return position.moveWest(value);
        }
        if (action == 'L') {
            return position.rotateLeftWise(value);
        }
        if (action == 'R') {
            return position.rotateRightWise(value);
        }
        if (action == 'F') {
            return position.moveForward(value);
        }

        return position;
    }
}

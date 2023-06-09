package _2022.day12;

import static _2022.day12.HillClimbingAlgorithm.NOT_VISITED_DISTANCE_VALUE;

import java.util.ArrayDeque;
import java.util.Queue;

public class NeighborsVisitor {

    private final int[][] heightsMatrix;
    private final int[][] distancesMap;

    private final Position POSITION_TO_LEFT = new Position(0, -1);
    private final Position POSITION_TO_RIGHT = new Position(0, 1);
    private final Position POSITION_TO_UP = new Position(-1, 0);
    private final Position POSITION_TO_DOWN = new Position(1, 0);

    public NeighborsVisitor(int[][] heightsMatrix, int[][] distancesMap) {
        this.heightsMatrix = heightsMatrix;
        this.distancesMap = distancesMap;
    }

    public Queue<Position> visit(Position currentPosition) {
        int row = currentPosition.row();
        int column = currentPosition.column();

        Queue<Position> queue = new ArrayDeque<>();
        int currentHeight = heightsMatrix[row][column];
        int nextDistance = distancesMap[currentPosition.row()][currentPosition.column()] + 1;

        checkNeighbor(POSITION_TO_LEFT, currentPosition, queue, currentHeight, nextDistance);
        checkNeighbor(POSITION_TO_RIGHT, currentPosition, queue, currentHeight, nextDistance);
        checkNeighbor(POSITION_TO_UP, currentPosition, queue, currentHeight, nextDistance);
        checkNeighbor(POSITION_TO_DOWN, currentPosition, queue, currentHeight, nextDistance);

        return queue;
    }

    private void checkNeighbor(
            Position positionShift,
            Position currentPosition,
            Queue<Position> queue,
            int currentHeight,
            int nextDistance) {

        Position shiftedPosition = currentPosition.shift(positionShift);
        int row = shiftedPosition.row();
        int column = shiftedPosition.column();

        if (isNeighborVisitable(row, column, currentHeight)) {
            queue.add(new Position(row, column));
            distancesMap[row][column] = nextDistance;
        }
    }

    private boolean isNeighborVisitable(int row, int column, int currentHeight) {
        return isNotOutOfBounds(heightsMatrix, row, column)
                && isNotHigherThan1Above(currentHeight, heightsMatrix[row][column])
                && isNotVisited(distancesMap[row][column]);
    }

    private static boolean isNotOutOfBounds(int[][] heightsMatrix, int row, int column) {
        return row >= 0 && row < heightsMatrix.length &&
                column >= 0 && column < heightsMatrix[0].length;
    }

    private static boolean isNotVisited(int positionDistance) {
        return NOT_VISITED_DISTANCE_VALUE == positionDistance;
    }

    private static boolean isNotHigherThan1Above(int currentHeight, int neighborHeight) {
        return neighborHeight <= currentHeight + 1;
    }
}
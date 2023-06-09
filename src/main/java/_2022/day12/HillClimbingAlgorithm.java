package _2022.day12;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import lombok.extern.java.Log;
import util.SolvableTask;

@Log
public class HillClimbingAlgorithm implements SolvableTask {

    private static final int A_VALUE = 1;
    private static final int START_VALUE = 0;
    private static final int END_VALUE = 27;
    private static final int CHAR_BASE = 96;
    protected static final int NOT_VISITED_DISTANCE_VALUE = 99;

    public static void main(String[] args) {
        new HillClimbingAlgorithm().solve();
    }

    @Override
    public void solve() {
        String[] inputLines = getInputLines();

        int[][] heightsMatrix = extractHeights(inputLines);

        Position end = findPositions(heightsMatrix, END_VALUE).get(0);
        List<Position> startPositions = findPositions(heightsMatrix, START_VALUE);
        startPositions.addAll(findPositions(heightsMatrix, A_VALUE));

        int shortestPath = startPositions.stream()
                .map(start -> BFS(heightsMatrix, start, end))
                .filter(Objects::nonNull)
                .mapToInt(map -> map[end.row()][end.column()])
                .min()
                .orElseThrow();

        log.info(String.valueOf(shortestPath));

    }

    private static int[][] extractHeights(String[] inputLines) {
        return Arrays.stream(inputLines)
                .map(line -> Arrays.stream(line.split(""))
                        .mapToInt(height -> switch (height) {
                            case "S" -> START_VALUE;
                            case "E" -> END_VALUE;
                            default -> height.codePointAt(0) - CHAR_BASE;
                        })
                        .toArray())
                .toArray(int[][]::new);
    }

    private static List<Position> findPositions(int[][] heightsMatrix, int valueToFind) {
        List<Position> positions = new ArrayList<>();

        for (int i = 0; i < heightsMatrix.length; i++) {
            for (int j = 0; j < heightsMatrix[0].length; j++) {
                if (heightsMatrix[i][j] == valueToFind) {
                    positions.add(new Position(i, j));
                }
            }
        }

        return positions;
    }

    private static int[][] BFS(int[][] heightsMatrix, Position start, Position end) {
        int[][] distancesMap = createAndPrefillDistanceMapWithUnvisited(heightsMatrix);

        Queue<Position> queue = new ArrayDeque<>();
        queue.add(start);
        distancesMap[start.row()][start.column()] = 0;
        NeighborsVisitor visitor = new NeighborsVisitor(heightsMatrix, distancesMap);

        while (!queue.isEmpty()) {
            Position currentPosition = queue.remove();

            if (currentPosition.equals(end)) {
                return distancesMap;
            }

            Queue<Position> queueToVisit = visitor.visit(currentPosition);

            queue.addAll(queueToVisit);
        }

        return null;
    }

    private static int[][] createAndPrefillDistanceMapWithUnvisited(int[][] heightsMatrix) {
        int[][] distancesMap = new int[heightsMatrix.length][heightsMatrix[0].length];
        for (int[] row : distancesMap) {
            Arrays.fill(row, NOT_VISITED_DISTANCE_VALUE);
        }
        return distancesMap;
    }

}
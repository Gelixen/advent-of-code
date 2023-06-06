package _2022.day8;

import java.util.Arrays;
import java.util.function.Function;
import lombok.extern.java.Log;
import util.SolvableTask;

@Log
public class TreetopTreeHouse implements SolvableTask {

    public static void main(String[] args) {
        new TreetopTreeHouse().solve();
    }

    @Override
    public void solve() {
        int[][] treeHeightsMatrix = getTreeHeightsMatrix();

        int columnCount = treeHeightsMatrix[0].length;
        int rowCount = Math.toIntExact(Arrays.stream(treeHeightsMatrix).count());

        int scenicScore = 0;

        for (int rowIndex = 1; rowIndex < rowCount - 1; rowIndex++) {
            for (int columnIndex = 1; columnIndex < columnCount - 1; columnIndex++) {
                int value = treeHeightsMatrix[rowIndex][columnIndex];

                Function<Integer, Integer> decrementFunction = row -> row - 1;
                Function<Integer, Integer> incrementFunction = column -> column + 1;

                int left = findViewDistanceTillEdge(
                        treeHeightsMatrix,
                        value,
                        decrementFunction.apply(rowIndex),
                        columnIndex,
                        decrementFunction,
                        Function.identity()
                );
                int right = findViewDistanceTillEdge(
                        treeHeightsMatrix,
                        value,
                        incrementFunction.apply(rowIndex),
                        columnIndex,
                        incrementFunction,
                        Function.identity()
                );
                int up = findViewDistanceTillEdge(
                        treeHeightsMatrix,
                        value,
                        rowIndex,
                        decrementFunction.apply(columnIndex),
                        Function.identity(),
                        decrementFunction
                );
                int down = findViewDistanceTillEdge(
                        treeHeightsMatrix,
                        value,
                        rowIndex,
                        incrementFunction.apply(columnIndex),
                        Function.identity(),
                        incrementFunction
                );

                int temp = left * right * up * down;

                if (temp > scenicScore) {
                    scenicScore = temp;
                }
            }
        }

        log.info(String.valueOf(scenicScore));
    }

    private int[][] getTreeHeightsMatrix() {
        return Arrays.stream(getInputLines())
                .map(row -> Arrays.stream(row.split("")).mapToInt(Integer::parseInt).toArray())
                .toArray(int[][]::new);
    }

    private static int findViewDistanceTillEdge(
            int[][] treeHeightsMatrix,
            int initialValue,
            Integer rowIndex,
            Integer columnIndex,
            Function<Integer, Integer> rowModifier,
            Function<Integer, Integer> columnModifier) {

        if (rowIndex >= treeHeightsMatrix.length
                || rowIndex < 0
                || columnIndex >= treeHeightsMatrix[0].length
                || columnIndex < 0) {
            return 0;
        }

        int value = treeHeightsMatrix[rowIndex][columnIndex];

        if (value >= initialValue) {
            return 1;
        }

        return 1 + findViewDistanceTillEdge(
                treeHeightsMatrix,
                initialValue,
                rowModifier.apply(rowIndex),
                columnModifier.apply(columnIndex),
                rowModifier,
                columnModifier
        );
    }

}
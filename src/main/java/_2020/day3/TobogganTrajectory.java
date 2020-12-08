package _2020.day3;

import lombok.extern.java.Log;
import util.SolvableTask;

import java.util.stream.IntStream;

@Log
public class TobogganTrajectory implements SolvableTask {

    private static final char TREE_SYMBOL = '#';

    private char[][] dataMatrix;
    private int matrixWidth;
    private int matrixHeight;

    public static void main(String[] args) {
        new TobogganTrajectory().solve();
    }

    @Override
    public void solve() {
        prepareData();

        int result = findTreesCount(1, 1);
        result *= findTreesCount(1, 3);
        result *= findTreesCount(1, 5);
        result *= findTreesCount(1, 7);
        result *= findTreesCount(2, 1);

        log.info(String.valueOf(result));
    }

    private void prepareData() {
        String[] inputLines = getInputLines();

        matrixWidth = inputLines[0].length();
        matrixHeight = inputLines.length;
        dataMatrix = new char[matrixHeight][matrixWidth];

        IntStream.range(0, matrixHeight)
                .forEach(index -> dataMatrix[index] = inputLines[index].toCharArray());
    }

    private int findTreesCount(int heightIncrement, int widthIncrement) {
        int slopeHeight = 0;
        int slopeWidth = 0;
        int treeCount = 0;

        do {
            if (dataMatrix[slopeHeight % matrixHeight][slopeWidth % matrixWidth] == TREE_SYMBOL)
                treeCount++;

            slopeHeight += heightIncrement;
            slopeWidth += widthIncrement;

        } while (slopeHeight < dataMatrix.length);

        return treeCount;
    }

}

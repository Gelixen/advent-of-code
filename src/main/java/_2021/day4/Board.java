package _2021.day4;

import java.util.Arrays;
import java.util.function.BiFunction;

public class Board {
    protected final int[][] board = new int[5][5];
    protected final boolean[][] markingBoard = new boolean[5][5];

    public void setRow(int rowNumber, int[] intNumbers) {
        board[rowNumber] = intNumbers;
    }

    public void print() {
        for (int[] row : board) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }

    protected int[] getRow(int rowIndex) {
        return board[rowIndex];
    }

    public void markNumberDraw(int numberDraw) {
        for (int rowIndex = 0; rowIndex < 5; rowIndex++) {
            int[] row = board[rowIndex];

            for (int numberIndex = 0; numberIndex < 5; numberIndex++) {
                int number = row[numberIndex];
                if (number == numberDraw) {
                    markingBoard[rowIndex][numberIndex] = true;
                }
            }
        }
    }

    public boolean winConditionMet() {
        return (isLineWin(rowChecker()) || isLineWin(columnChecker()));
    }

    private boolean isLineWin(BiFunction<Integer, Integer, Boolean> checker) {

        for (int primaryIndex = 0; primaryIndex < 5; primaryIndex++) {
            int markCount = 0;

            for (int secondaryIndex = 0; secondaryIndex < 5; secondaryIndex++) {
                if (checker.apply(primaryIndex, secondaryIndex)) {
                    markCount++;
                }
            }

            if (markCount == 5) {
                return true;
            }
        }

        return false;
    }

    private BiFunction<Integer, Integer, Boolean> rowChecker() {
        return (rowIndex, columnIndex) -> markingBoard[rowIndex][columnIndex];
    }

    private BiFunction<Integer, Integer, Boolean> columnChecker() {
        return (rowIndex, columnIndex) -> markingBoard[columnIndex][rowIndex];
    }

    public int getUnmarkedNumbersSum() {
        int sum = 0;

        for (int rowIndex = 0; rowIndex < 5; rowIndex++) {
            for (int columnIndex = 0; columnIndex < 5; columnIndex++) {
                boolean isMarked = markingBoard[rowIndex][columnIndex];

                if (!isMarked) {
                    sum += board[rowIndex][columnIndex];
                }
            }
        }

        return sum;
    }
}

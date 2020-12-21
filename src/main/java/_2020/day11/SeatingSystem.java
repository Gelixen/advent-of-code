package _2020.day11;

import lombok.extern.java.Log;
import util.SolvableTask;

import java.util.Arrays;

import static _2020.day11.PositionState.EMPTY_SEAT;
import static _2020.day11.PositionState.OCCUPIED_SEAT;

@Log
public class SeatingSystem implements SolvableTask {

    private char[][] seatsMatrix = Arrays.stream(getInputLines()).map(String::toCharArray).toArray(char[][]::new);
    private final SeatsCounter seatsCounter;

    SeatingSystem() {
        this(new SeatsCounter());
    }

    SeatingSystem(SeatsCounter seatsCounter) {
        this.seatsCounter = seatsCounter;
    }

    public static void main(String[] args) {
        new SeatingSystem().solve();
    }

    @Override
    public void solve() {
        iterateRoundsTillNoChanges();

        int result = getOccupiedSeatsCount();

        log.info(String.valueOf(result));
    }

    char[][] iterateRoundsTillNoChanges() {
        while (hasAnyStateChangedIterating()) {
            printMatrix();
        }

        return seatsMatrix;
    }

    int getOccupiedSeatsCount() {
        int count = 0;

        for (char[] matrix : seatsMatrix) {
            for (int j = 0; j < seatsMatrix[0].length; j++) {
                if (matrix[j] == OCCUPIED_SEAT.getSymbol()) {
                    count++;
                }
            }
        }

        return count;
    }

    private void printMatrix() {
        for (char[] matrix : seatsMatrix) {
            for (int j = 0; j < seatsMatrix[0].length; j++) {
                System.out.print(matrix[j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean hasAnyStateChangedIterating() {
        boolean hasStateChanged = false;

        char[][] seatsMatrixCopy = new char[seatsMatrix.length][];
        for (int i = 0; i < seatsMatrix.length; i++) {
            seatsMatrixCopy[i] = seatsMatrix[i].clone();
        }

        for (int i = 0; i < seatsMatrixCopy.length; i++) {
            for (int j = 0; j < seatsMatrixCopy[0].length; j++) {
                char newState = calculateNewState(i, j);
                char oldState = seatsMatrixCopy[i][j];
                if (oldState != newState) {
                    seatsMatrixCopy[i][j] = newState;
                    hasStateChanged = true;
                }
            }
        }
        seatsMatrix = seatsMatrixCopy;

        return hasStateChanged;
    }

    public char calculateNewState(int rowIndex, int columnIndex) {
        PositionState currentState = PositionState.fromSymbol(seatsMatrix[rowIndex][columnIndex]);
        PositionState newState = currentState;

        if (EMPTY_SEAT.equals(currentState) && !isAdjacentOccupiedSeatsCountExceeds(0, rowIndex, columnIndex)) {
            newState = OCCUPIED_SEAT;
        }

        if (OCCUPIED_SEAT.equals(currentState) && isAdjacentOccupiedSeatsCountExceeds(3, rowIndex, columnIndex)) {
            newState = EMPTY_SEAT;
        }

        return newState.getSymbol();
    }

    private boolean isAdjacentOccupiedSeatsCountExceeds(int limit, int rowIndex, int columnIndex) {
        int occupiedSeatsCount = seatsCounter.withMatrix(seatsMatrix).countAdjacentOccupiedSeats(rowIndex, columnIndex);

        return occupiedSeatsCount > limit;
    }

}

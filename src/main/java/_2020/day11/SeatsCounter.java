package _2020.day11;

import static _2020.day11.PositionState.FLOOR;
import static _2020.day11.PositionState.OCCUPIED_SEAT;

public class SeatsCounter {

    private char[][] seatsMatrix;
    private int rowsLength;
    private int columnsLength;

    SeatsCounter withMatrix(char[][] seatsMatrix) {
        this.seatsMatrix = seatsMatrix;
        this.rowsLength = seatsMatrix.length;
        this.columnsLength = seatsMatrix[0].length;

        return this;
    }

    int countAdjacentOccupiedSeats(int rowIndex, int columnIndex) {
        int occupiedSeatsCount = 0;

        for (SurroundingPosition value : SurroundingPosition.values()) {
            boolean isPositionLookupFinished = false;

            for (int i = 1; !isPositionLookupFinished; i++) {
                int row = rowIndex + value.getRow() * i;
                int column = columnIndex + value.getColumn() * i;

                isPositionLookupFinished = canPositionLookupFinish(row, column);
                occupiedSeatsCount += getOccupiedSeatsCountIncrement(row, column);
            }
        }

        return occupiedSeatsCount;
    }

    private boolean canPositionLookupFinish(int row, int column) {
        if (!isInsideMatrix(row, column)) {
            return true;
        }

        return !isSeat(FLOOR, row, column);
    }

    private int getOccupiedSeatsCountIncrement(int row, int column) {
        return isInsideMatrix(row, column) && isSeat(OCCUPIED_SEAT, row, column)
                ? 1
                : 0;
    }

    private boolean isInsideMatrix(int row, int column) {
        return isRowInsideMatrix(row) && isColumnInsideMatrix(column);
    }

    private boolean isRowInsideMatrix(int row) {
        return row >= 0 && row < rowsLength;
    }

    private boolean isColumnInsideMatrix(int column) {
        return column >= 0 && column < columnsLength;
    }

    private boolean isSeat(PositionState occupiedSeat, int row, int column) {
        char symbol = seatsMatrix[row][column];
        return occupiedSeat.equalsBySymbol(symbol);
    }

}
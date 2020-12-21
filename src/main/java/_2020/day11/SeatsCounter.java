package _2020.day11;

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
            int row = rowIndex + value.getRow();
            int column = columnIndex + value.getColumn();
            if (isInsideMatrix(row, column) && isOccupiedSeat(row, column)) {
                occupiedSeatsCount++;
            }
        }

        return occupiedSeatsCount;
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

    private boolean isOccupiedSeat(int row, int column) {
        return OCCUPIED_SEAT.equalsBySymbol(seatsMatrix[row][column]);
    }

}
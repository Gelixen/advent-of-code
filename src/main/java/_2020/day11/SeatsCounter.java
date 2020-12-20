package _2020.day11;

import static _2020.day11.PositionState.OCCUPIED_SEAT;

public class SeatsCounter {

    int countAdjacentOccupiedSeats(char[][] seatsMatrix, int rowIndex, int columnIndex) {
        int occupiedSeatsCount = 0;

        for (int i = rowIndex - 1; i <= rowIndex + 1; i++) {
            if (i >= 0 && i < seatsMatrix.length) {
                for (int j = columnIndex - 1; j <= columnIndex + 1; j++) {
                    if (j >= 0 && j < seatsMatrix[0].length)
                        if (OCCUPIED_SEAT.equalsBySymbol(seatsMatrix[i][j]) && (rowIndex != i || columnIndex != j)) {
                            occupiedSeatsCount++;
                        }
                }
            }
        }

        return occupiedSeatsCount;
    }
}
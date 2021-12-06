package _2021.day4;

import java.util.Arrays;

public class BoardConstructor {

    private final Board board = new Board();

    public BoardConstructor(String[] binaryNumbers, int startIndex) {
        for (int i = 0; i < 5; i++) {
            int currentRowIndex = startIndex + i;
            String boardRow = binaryNumbers[currentRowIndex];
            String[] splitNumbers = boardRow.strip().replaceAll(" +", " ").split(" ");

            int[] intNumbers = Arrays.stream(splitNumbers)
                    .mapToInt(Integer::parseInt)
                    .toArray();

            board.setRow(i, intNumbers);
        }
    }

    public Board getBoard() {
        return board;
    }
}

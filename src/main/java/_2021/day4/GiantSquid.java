package _2021.day4;

import lombok.extern.java.Log;
import util.SolvableTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.function.Predicate.not;

@Log
public class GiantSquid implements SolvableTask {

    private List<Board> boards = new ArrayList<>();

    public GiantSquid() {}

    public static void main(String[] args) {
        new GiantSquid().solve();
    }

    @Override
    public void solve() {
        String[] binaryNumbers = getInputLines();
        int[] numberDraws = Arrays.stream(binaryNumbers[0].split(","))
                .mapToInt(Integer::parseInt)
                .toArray();

        boards = extractBoards(binaryNumbers);

        int finalScore = getFinalScore(numberDraws);

        log.info(String.valueOf(finalScore));
    }

    private List<Board> extractBoards(String[] binaryNumbers) {
        List<Board> boards = new ArrayList<>();
        int boardsCount = binaryNumbers.length / 6;
        int boardsCounter = 0;

        while (boardsCounter < boardsCount) {
            Board board = new BoardConstructor(binaryNumbers, 2 + 6 * boardsCounter++).getBoard();
            boards.add(board);
        }

        return boards;
    }

    private int getFinalScore(int[] numberDraws) {
        int latestWinFinalScore = 0;
        Integer winningScore;
        for (int numberDraw : numberDraws) {
            drawNumberFromBoards(numberDraw);

            winningScore = checkForWinningBoardScore();

            if (winningScore != null && winningScore != 0) {
                latestWinFinalScore = winningScore * numberDraw;
            }
        }
        return latestWinFinalScore;
    }

    private void drawNumberFromBoards(int numberDraw) {
        boards.forEach(board -> board.markNumberDraw(numberDraw));
    }

    private Integer checkForWinningBoardScore() {
        return boards.stream()
                .filter(not(Board::isMarked))
                .filter(Board::winConditionMet)
                .map(Board::getUnmarkedNumbersSum)
                .reduce(0, (a, b) -> b);
    }
}

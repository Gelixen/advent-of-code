package _2022.day2;

import lombok.extern.java.Log;
import util.SolvableTask;

import java.util.Arrays;

@Log
public class RockPaperScissors implements SolvableTask {
    public static void main(String[] args) {
        new RockPaperScissors().solve();
    }

    @Override
    public void solve() {
        System.out.println(countTotal(getInputLines()));
    }

    private int countTotal(String[] roundLine) {
        return Arrays.stream(roundLine).map(round -> {
            String[] handShapes = round.split(" ");
            char player1HandShapeSymbol = handShapes[0].charAt(0);
            char player2HandShapeSymbol = handShapes[1].charAt(0);
            return new Round(player1HandShapeSymbol, player2HandShapeSymbol);
        }).map(Round::calculateScore).mapToInt(i -> i).sum();
    }
}

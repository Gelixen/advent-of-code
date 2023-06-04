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
            String[] roundSymbols = round.split(" ");
            char handShapeSymbol = roundSymbols[0].charAt(0);
            char roundEndSymbol = roundSymbols[1].charAt(0);
            return new Round(handShapeSymbol, roundEndSymbol);
        }).map(Round::calculateScore).mapToInt(i -> i).sum();
    }
}

package _2022.day2;

import static _2022.day2.HandShape.*;

public class Round {

    private final HandShape handShape;
    private final RoundEnd roundEnd;

    public Round(char handShapeSymbol, char roundEndSymbol) {
        this.handShape = HandShape.fromSymbol(handShapeSymbol);
        this.roundEnd = RoundEnd.fromSymbol(roundEndSymbol);
    }

    public int calculateScore() {
        HandShape requiredHandShape = switch (roundEnd) {
            case DRAW -> handShape;
            case WIN -> switch (handShape) {
                case ROCK -> PAPER;
                case PAPER -> SCISSORS;
                case SCISSORS -> ROCK;
            };
            case LOSE -> switch (handShape) {
                case ROCK -> SCISSORS;
                case PAPER -> ROCK;
                case SCISSORS -> PAPER;
            };
        };

        return roundEnd.getScore() + requiredHandShape.getScore();
    }

}

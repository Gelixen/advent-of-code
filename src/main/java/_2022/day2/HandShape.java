package _2022.day2;

import java.util.List;

public enum HandShape {
    ROCK(1, List.of('A', 'X')),
    PAPER(2, List.of('B', 'Y')),
    SCISSORS(3, List.of('C', 'Z'));

    private final int score;
    private final List<Character> symbols;

    HandShape(int score, List<Character> symbols) {
        this.score = score;
        this.symbols = symbols;
    }

    public static HandShape fromSymbol(char symbol) {
        for (HandShape shape : HandShape.values()) {
            if (shape.symbols.contains(symbol)) {
                return shape;
            }
        }
        throw new HandShapeNotFoundException(symbol);
    }


    public int getScore() {
        return score;
    }
}

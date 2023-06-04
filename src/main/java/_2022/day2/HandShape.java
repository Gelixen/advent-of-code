package _2022.day2;

public enum HandShape {
    ROCK(1, 'A'),
    PAPER(2, 'B'),
    SCISSORS(3, 'C');

    private final int score;
    private final char symbol;

    HandShape(int score, char symbol) {
        this.score = score;
        this.symbol = symbol;
    }

    public static HandShape fromSymbol(char symbol) {
        for (HandShape shape : HandShape.values()) {
            if (shape.symbol == symbol) {
                return shape;
            }
        }
        throw new HandShapeNotFoundException(symbol);
    }


    public int getScore() {
        return score;
    }
}

package _2022.day2;

public enum RoundEnd {
    LOSE(0, 'X'),
    DRAW(3, 'Y'),
    WIN(6, 'Z');

    private final int score;
    private final char symbol;

    RoundEnd(int score, char symbol) {
        this.score = score;
        this.symbol = symbol;
    }

    public static RoundEnd fromSymbol(char symbol) {
        for (RoundEnd roundEnd : RoundEnd.values()) {
            if (roundEnd.symbol == symbol) {
                return roundEnd;
            }
        }
        throw new RoundEndNotFoundException(symbol);
    }


    public int getScore() {
        return score;
    }
}

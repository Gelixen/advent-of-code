package _2022.day9;

public enum Direction {
    UP('U', 0, 1),
    DOWN('D', 0, -1),
    LEFT('L', -1, 0),
    RIGHT('R', 1, 0);

    private final char symbol;
    private final int xChange;
    private final int yChange;

    Direction(char symbol, int xChange, int yChange) {
        this.symbol = symbol;
        this.xChange = xChange;
        this.yChange = yChange;
    }

    public static Direction fromSymbol(char symbol) {
        for (Direction direction : Direction.values()) {
            if (direction.symbol == symbol) {
                return direction;
            }
        }
        throw new DirectionNotFoundException(symbol);
    }

    public int getxChange() {
        return xChange;
    }

    public int getyChange() {
        return yChange;
    }
}
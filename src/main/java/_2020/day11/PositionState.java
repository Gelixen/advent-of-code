package _2020.day11;

public enum PositionState {
    FLOOR('.'),
    EMPTY_SEAT('L'),
    OCCUPIED_SEAT('#');

    private final char symbol;

    PositionState(char symbol) {
        this.symbol = symbol;
    }

    public static PositionState fromSymbol(char symbol) {
        for (PositionState state : PositionState.values()) {
            if (state.symbol == symbol) {
                return state;
            }
        }
        throw new PositionStateNotFoundException(symbol);
    }

    boolean equalsBySymbol(char symbol) {
        return this.symbol == symbol;
    }

    public char getSymbol() {
        return symbol;
    }
}
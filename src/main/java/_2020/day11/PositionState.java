package _2020.day11;

import lombok.Getter;

public enum PositionState {
    FLOOR('.'),
    EMPTY_SEAT('L'),
    OCCUPIED_SEAT('#');

    @Getter
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
}

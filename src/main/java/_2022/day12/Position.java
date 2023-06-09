package _2022.day12;

public record Position(int row, int column) {

    public Position shift(Position shift) {
        return new Position(this.row + shift.row, this.column + shift.column);
    }
}
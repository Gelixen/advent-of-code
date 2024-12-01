package _2020.day5;

public record Seat(int row, int column) {

    public int getId() {
        return row * 8 + column;
    }
}
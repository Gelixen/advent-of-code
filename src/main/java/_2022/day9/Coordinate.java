package _2022.day9;

public record Coordinate(int x, int y) {

    public Coordinate add(Coordinate another) {
        return new Coordinate(x + another.x(), y + another.y());
    }
}
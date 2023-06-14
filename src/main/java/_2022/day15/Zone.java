package _2022.day15;

public record Zone(Coordinate sensor, Coordinate beacon) {

    public int getManhattanDistance() {
        return Math.abs(sensor.x() - beacon.x()) + Math.abs(sensor.y() - beacon.y());
    }
}

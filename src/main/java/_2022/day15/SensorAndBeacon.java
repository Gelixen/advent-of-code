package _2022.day15;

public record SensorAndBeacon(Coordinate sensor, Coordinate beacon) {

    public int getManhattanDistance() {
        return Math.abs(sensor.x() - beacon.x()) + Math.abs(sensor.y() - beacon.y());
    }
}

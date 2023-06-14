package _2022.day15;

import lombok.extern.java.Log;
import util.SolvableTask;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Log
public class BeaconExclusionZone implements SolvableTask {

    private static final int LOWER_AXIS_LIMIT = 0;
    private static final int HIGHER_AXIS_LIMIT = 4_000_000;

    public static void main(String[] args) {
        new BeaconExclusionZone().solve();
    }

    @Override
    public void solve() {
        String[] lines = getInputLines();

        Set<Zone> zones = Arrays.stream(lines)
                .map(ZoneExtractor::new)
                .map(ZoneExtractor::extract)
                .collect(Collectors.toSet());

        Set<Coordinate> externalLayerCoordinates = zones.stream()
                .flatMap(BeaconExclusionZone::getExternalLayerCoordinates)
                .collect(Collectors.toSet());


        Coordinate distressBeaconCoordinate = externalLayerCoordinates.stream()
                .filter(coordinate -> isNotWithinAnySensorRange(zones, coordinate))
                .findFirst()
                .orElseThrow();

        long tuningFrequency = (long) distressBeaconCoordinate.x() * HIGHER_AXIS_LIMIT + distressBeaconCoordinate.y();

        log.info(String.valueOf(tuningFrequency));
    }

    private static Stream<Coordinate> getExternalLayerCoordinates(Zone zone) {
        Coordinate sensor = zone.sensor();
        int radius = zone.getManhattanDistance();

        int lowestYOutside = Math.max(LOWER_AXIS_LIMIT, sensor.y() - radius - 1);
        int highestYOutside = Math.min(HIGHER_AXIS_LIMIT, sensor.y() + radius + 1);

        return IntStream.rangeClosed(lowestYOutside, highestYOutside)
                .boxed()
                .flatMap(movingY -> {
                    int verticalDistanceToMovingY = Math.abs(sensor.y() - movingY);
                    int remainderForXAxisDistance = zone.getManhattanDistance() - verticalDistanceToMovingY;

                    int higherXOutsideForMovingY = sensor.x() + remainderForXAxisDistance + 1;
                    int lowerXOutsideForMovingY = sensor.x() - remainderForXAxisDistance - 1;

                    Set<Coordinate> externalPoints = new HashSet<>();

                    if (isNotOutsideTheLimits(higherXOutsideForMovingY)) {
                        externalPoints.add(new Coordinate(higherXOutsideForMovingY, movingY));
                    }

                    if (isNotOutsideTheLimits(lowerXOutsideForMovingY)) {
                        externalPoints.add(new Coordinate(lowerXOutsideForMovingY, movingY));
                    }

                    return externalPoints.stream();
                });
    }

    private static boolean isNotOutsideTheLimits(int higherXOutsideForMovingY) {
        return higherXOutsideForMovingY >= LOWER_AXIS_LIMIT && higherXOutsideForMovingY <= HIGHER_AXIS_LIMIT;
    }


    private static boolean isNotWithinAnySensorRange(Set<Zone> zones, Coordinate coordinate) {
        return zones.stream().noneMatch(zone -> isWithinSensorRange(zone, coordinate));
    }

    private static boolean isWithinSensorRange(Zone zone, Coordinate coordinate) {
        Coordinate sensor = zone.sensor();
        int radius = zone.getManhattanDistance();

        int xDifference = Math.abs(sensor.x() - coordinate.x());
        int yDifference = Math.abs(sensor.y() - coordinate.y());
        int manhattanDistanceToCoordinate = xDifference + yDifference;

        return radius >= manhattanDistanceToCoordinate;
    }

}
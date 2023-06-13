package _2022.day14;

import lombok.extern.java.Log;
import util.SolvableTask;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static _2022.day14.CoordinateType.*;

@Log
public class RegolithReservoir implements SolvableTask {

    private static final Coordinate SAND_POUR_POINT = new Coordinate(SAND_SPAWNER, 0, 500);
    private static final int FLOOR_LEVEL_OFFSET = 2;

    public static void main(String[] args) {
        new RegolithReservoir().solve();
    }

    @Override
    public void solve() {
        String[] lines = getInputLines();

        List<Coordinate> coordinates = getCoordinates(lines);

        Integer sandUnits = loopTillSandReachesAbyss(coordinates);

        log.info(String.valueOf(sandUnits));
    }

    private static Integer loopTillSandReachesAbyss(List<Coordinate> coordinates) {
        IntSummaryStatistics xAxis = coordinates.stream().mapToInt(Coordinate::x).summaryStatistics();
        IntSummaryStatistics yAxis = coordinates.stream().mapToInt(Coordinate::y).summaryStatistics();

        System.out.println(xAxis.getMin() + " - " + xAxis.getMax());
        System.out.println(yAxis.getMin() + " - " + yAxis.getMax());

        int floorLevel = xAxis.getMax() + FLOOR_LEVEL_OFFSET;

        int yMin = yAxis.getMin() - floorLevel;
        int yMax = yAxis.getMax() + floorLevel;
        IntStream.range(yMin, yMax)
                .mapToObj(y -> new Coordinate(ROCK, floorLevel, y))
                .forEach(coordinates::add);

        int settledCount = 0;
        Coordinate sandCoordinate = SAND_POUR_POINT;

        while (true) {
            if (isBlocked(coordinates, sandCoordinate.getCoordinateBelow())) {
                if (isBlocked(coordinates, sandCoordinate.getCoordinateBelowRight())) {
                    if (isBlocked(coordinates, sandCoordinate.getCoordinateBelowLeft())) {
                        if (SAND_POUR_POINT.equals(sandCoordinate)) {
                            return settledCount + 1;
                        }

                        settledCount++;
                        coordinates.add(sandCoordinate);
                        sandCoordinate = SAND_POUR_POINT;
                    } else {
                        sandCoordinate = sandCoordinate.getCoordinateBelowLeft();
                    }
                } else {
                    sandCoordinate = sandCoordinate.getCoordinateBelowRight();
                }
            } else {
                sandCoordinate = sandCoordinate.getCoordinateBelow(SAND);
            }
        }
    }

    private static boolean isBlocked(List<Coordinate> coordinates, Coordinate currentSandCoordinate) {
        return coordinates.stream()
                .anyMatch(coordinate -> coordinate.equals(currentSandCoordinate));
    }

    private List<Coordinate> getCoordinates(String[] lines) {
        return Arrays.stream(lines)
                .flatMap(line -> {
                    String[] coordinatesString = line.split(" -> ");
                    List<Coordinate> lineCoordinates = new ArrayList<>();

                    for (int i = 0; i < coordinatesString.length - 1; i++) {
                        Coordinate first = mapToCoordinate(coordinatesString[i]);
                        Coordinate second = mapToCoordinate(coordinatesString[i + 1]);
                        lineCoordinates.addAll(extractCoordinatesLine(first, second));
                    }

                    return lineCoordinates.stream();

                }).collect(Collectors.toList());
    }

    private List<Coordinate> extractCoordinatesLine(Coordinate first, Coordinate second) {
        if (first.x() == second.x()) {
            return generateCoordinatesLine(first.y(), second.y(), (y) -> new Coordinate(ROCK, first.x(), y));
        }

        if (first.y() == second.y()) {
            return generateCoordinatesLine(first.x(), second.x(), (x) -> new Coordinate(ROCK, x, first.y()));
        }

        return Collections.emptyList();
    }

    public List<Coordinate> generateCoordinatesLine(int firstValue, int secondValue, Function<Integer, Coordinate> coordinateFunction) {
        int startValue = Math.min(firstValue, secondValue);
        int endValue = Math.max(firstValue, secondValue);
        return IntStream.rangeClosed(startValue, endValue)
                .mapToObj(coordinateFunction::apply)
                .toList();
    }

    public Coordinate mapToCoordinate(String coordinateString) {
        String[] coordinates = coordinateString.split(",");

        int x = Integer.parseInt(coordinates[1]);
        int y = Integer.parseInt(coordinates[0]);

        return new Coordinate(ROCK, x, y);
    }

}
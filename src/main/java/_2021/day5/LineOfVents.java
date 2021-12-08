package _2021.day5;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class LineOfVents {

    private Position startPosition;
    private Position endPosition;

    public List<Position> constructLine() {
        int xTrend = getTrend(startPosition.x(), endPosition.x());
        int yTrend = getTrend(startPosition.y(), endPosition.y());

        int rangeDifferenceX = Math.abs(startPosition.x() - endPosition.x());
        int rangeDifferenceY = Math.abs(startPosition.y() - endPosition.y());
        int rangeDifference = Math.max(rangeDifferenceX, rangeDifferenceY);

        return IntStream.rangeClosed(0, rangeDifference)
                .mapToObj(i -> new Position(
                        calculateConcreteCoord(startPosition.x(), xTrend, i),
                        calculateConcreteCoord(startPosition.y(), yTrend, i)
                )).collect(Collectors.toList());
    }

    private int calculateConcreteCoord(int baseCoord, int coordTrend, int stepNumber) {
        return baseCoord + coordTrend * stepNumber;
    }

    private int getTrend(int startCoord, int endCoord) {
        return Integer.compare(endCoord, startCoord);
    }
}

package _2021.day5;

import java.util.Collections;
import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record LineOfVents(Position startPosition, Position endPosition) {

    public List<Position> constructLine() {
        if (isVerticalLine()) {
            int x = startPosition.x();
            return getPositionsLine(startPosition.y(), endPosition.y(), y -> new Position(x, y));
        }

        if (isHorizontalLine()) {
            int y = startPosition.y();
            return getPositionsLine(startPosition.x(), endPosition.x(), x -> new Position(x, y));
        }

        return Collections.emptyList();
    }

    private List<Position> getPositionsLine(int startCoord, int endCoord, IntFunction<Position> createPosition) {
        int smallerCoord = Math.min(startCoord, endCoord);
        int biggerCoord = Math.max(startCoord, endCoord);

        return IntStream.rangeClosed(smallerCoord, biggerCoord)
                .mapToObj(createPosition)
                .collect(Collectors.toList());
    }

    private boolean isVerticalLine() {
        return startPosition.x() == endPosition.x();
    }

    private boolean isHorizontalLine() {
        return startPosition.y() == endPosition.y();
    }
}

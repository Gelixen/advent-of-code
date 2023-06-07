package _2022.day9;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import lombok.extern.java.Log;
import util.SolvableTask;

@Log
public class RopeBridge implements SolvableTask {

    private static final int HEAD_WITH_TAILS_COUNT = 10;
    private static final int HEAD_INDEX = 0;
    private static final int TAILS_START_INDEX = 1;
    private static final int LAST_TAIL_INDEX = 9;

    public static void main(String[] args) {
        new RopeBridge().solve();
    }

    @Override
    public void solve() {
        String[] inputLines = getInputLines();

        final Coordinate[] headAndTailsCoords = new Coordinate[HEAD_WITH_TAILS_COUNT];
        Arrays.fill(headAndTailsCoords, new Coordinate(0, 0));

        Set<Coordinate> lastTailVisitedUniquePositions = Arrays.stream(inputLines)
                .map(RopeBridge::toMotion)
                .flatMap(RopeBridge::toCoordinates)
                .map(vector -> {
                    headAndTailsCoords[HEAD_INDEX] = headAndTailsCoords[HEAD_INDEX].add(vector);

                    IntStream.range(TAILS_START_INDEX, HEAD_WITH_TAILS_COUNT)
                            .forEach(index ->
                                    headAndTailsCoords[index] = moveTail(
                                            headAndTailsCoords[index - 1],
                                            headAndTailsCoords[index]
                                    )
                            );

                    return headAndTailsCoords[LAST_TAIL_INDEX];

                })
                .collect(Collectors.toSet());

        log.info(String.valueOf(lastTailVisitedUniquePositions.size()));
    }

    private static Coordinate moveTail(Coordinate head, Coordinate tail) {

        int headX = head.x();
        int headY = head.y();
        int tailX = tail.x();
        int tailY = tail.y();

        if (headX == tailX) {
            int distance = headY - tailY;
            if (pointsDoNotTouch(distance)) {
                tailY = moveByOneToSignDirection(tailY, distance);
            }
        }
        if (headY == tailY) {
            int distance = headX - tailX;
            if (pointsDoNotTouch(distance)) {
                tailX = moveByOneToSignDirection(tailX, distance);
            }
        }

        if (headX != tailX && headY != tailY) {
            int xDistance = headX - tailX;
            int yDistance = headY - tailY;

            if (pointsDoNotTouch(xDistance) || pointsDoNotTouch(yDistance)) {
                tailX = moveByOneToSignDirection(tailX, xDistance);
                tailY = moveByOneToSignDirection(tailY, yDistance);
            }
        }

        return new Coordinate(tailX, tailY);
    }

    private static boolean pointsDoNotTouch(int distance) {
        return Math.abs(distance) >= 2;
    }

    private static int moveByOneToSignDirection(int point, int distance) {
        if (distance > 0) {
            point += 1;
        } else {
            point -= 1;
        }
        return point;
    }

    private static Stream<Coordinate> toCoordinates(Motion motion) {
        return IntStream.range(0, motion.steps())
                .mapToObj(__ -> new Coordinate(
                                motion.direction().getxChange(),
                                motion.direction().getyChange()
                        )
                );
    }

    private static Motion toMotion(String line) {
        String[] command = line.split(" ");
        char directionSymbol = command[0].charAt(0);
        Direction direction = Direction.fromSymbol(directionSymbol);
        int steps = Integer.parseInt(command[1]);

        return new Motion(direction, steps);
    }

}
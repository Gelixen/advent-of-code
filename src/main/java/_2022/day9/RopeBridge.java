package _2022.day9;

import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import lombok.extern.java.Log;
import util.SolvableTask;

@Log
public class RopeBridge implements SolvableTask {

    public static void main(String[] args) {
        new RopeBridge().solve();
    }

    @Override
    public void solve() {
        String[] inputLines = getInputLines();

        AtomicReference<Coordinate> headCoordinate = new AtomicReference<>(new Coordinate(0, 0));
        AtomicReference<Coordinate> tailCoordinate = new AtomicReference<>(new Coordinate(0, 0));

        Set<Coordinate> x = Arrays.stream(inputLines)
                .map(RopeBridge::toMotion)
                .flatMap(RopeBridge::toCoordinates)
                .map(coordinate -> {
                    Coordinate head = headCoordinate.accumulateAndGet(coordinate, Coordinate::add);
                    Coordinate tail = tailCoordinate.get();

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

                    Coordinate newTailPosition = new Coordinate(tailX, tailY);
                    tailCoordinate.set(newTailPosition);
                    
                    return newTailPosition;
                    
                })
                .collect(Collectors.toSet());
        log.info(String.valueOf(x.size()));
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
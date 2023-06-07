package _2022.day10;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.extern.java.Log;
import util.SolvableTask;

@Log
public class CathodeRayTube implements SolvableTask {

    private static final int CRT_HEIGHT = 6;
    private static final int CRT_WIDTH = 40;

    public static void main(String[] args) {
        new CathodeRayTube().solve();
    }

    @Override
    public void solve() {
        String[] inputLines = getInputLines();

        AtomicInteger cycleCount = new AtomicInteger();
        AtomicInteger x = new AtomicInteger(1);

        Map<Integer, Integer> cycleAndXMap = Arrays.stream(inputLines)
                .flatMap(line -> {
                    String[] parts = line.split(" ");
                    String command = parts[0];

                    return switch (command) {
                        case "noop" -> Stream.of(Map.entry(cycleCount.incrementAndGet(), x.get()));
                        case "addx" -> {
                            int increment = Integer.parseInt(parts[1]);
                            yield Stream.of(
                                    Map.entry(cycleCount.incrementAndGet(), x.get()),
                                    Map.entry(cycleCount.incrementAndGet(), x.getAndAdd(increment))
                            );
                        }
                        default -> throw new IllegalStateException("Unexpected value: " + command);
                    };
                })
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue));

        char[][] crt = new char[CRT_HEIGHT][CRT_WIDTH];

        cycleAndXMap.keySet()
                .stream()
                .map(cycleNo -> new Data(
                                cycleNo % CRT_WIDTH,
                                (cycleNo - 1) / CRT_WIDTH,
                                (cycleNo - 1) % CRT_WIDTH,
                                cycleAndXMap.get(cycleNo)
                        )
                )
                .forEach(data -> {
                    if (cycleWithinXWindowOfThree(data)) {
                        crt[data.rowIndex()][data.columnIndex()] = '#';
                    } else {
                        crt[data.rowIndex()][data.columnIndex()] = '.';
                    }
                });

        for (char[] row : crt) {
            for (int column = 0; column < crt[0].length; column++) {
                System.out.print(row[column]);
            }
            System.out.println();
        }
    }

    private static boolean cycleWithinXWindowOfThree(Data data) {
        Integer cycle = data.normalizedCycle();
        Integer x = data.x();
        return cycle >= x && cycle <= x + 2;
    }

}
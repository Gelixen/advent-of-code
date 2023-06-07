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

        int sum = cycleAndXMap.keySet().stream()
                .filter(CathodeRayTube::is20OrFurtherRepetitionOf40)
                .map(g -> g * cycleAndXMap.get(g))
                .mapToInt(integer -> integer)
                .sum();

        log.info(String.valueOf(sum));
    }

    private static boolean is20OrFurtherRepetitionOf40(Integer cycle) {
        return (cycle - 20) % 40 == 0;
    }

}
package _2021.day1;

import org.junit.jupiter.api.Test;

import java.util.function.Consumer;
import java.util.stream.IntStream;

class SonarSweepTest {

    public static final int[] TEST_ARRAY = IntStream.generate(() -> (int) (Math.random() * 10000))
            .limit(30_000_000)
            .toArray();

    @Test
    void findDepthIncrements() {
        logTime(new SonarSweep()::findDepthIncrements);
    }

    private void logTime(Consumer<int[]> consumer) {
        consumer.accept(TEST_ARRAY);
        long startTime = System.nanoTime();
        for (int i = 0; i < 100; i++) {
            consumer.accept(TEST_ARRAY);
        }
        long endTime = System.nanoTime();
        System.out.println((endTime - startTime) / 100);
    }
}
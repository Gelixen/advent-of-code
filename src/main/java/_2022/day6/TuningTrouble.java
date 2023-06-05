package _2022.day6;

import lombok.extern.java.Log;
import util.SolvableTask;

import java.util.stream.IntStream;

@Log
public class TuningTrouble implements SolvableTask {

    public static void main(String[] args) {
        new TuningTrouble().solve();
    }

    @Override
    public void solve() {
        String input = getInputLines()[0];

        int windowSize = 14;

        IndexWithWindowPair indexWithWindowPair = IntStream.range(0, input.length() - windowSize)
                .mapToObj(index -> new IndexWithWindowPair(index, input.substring(index, index + windowSize)))
                .filter(pair -> pair.window().chars().distinct().count() == windowSize)
                .limit(1)
                .toList()
                .get(0);

        log.info(String.valueOf(indexWithWindowPair.index() + windowSize));
    }

}
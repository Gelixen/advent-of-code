package _2021.day6;

import lombok.extern.java.Log;
import util.SolvableTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Log
public class Lanternfish implements SolvableTask {

    public Lanternfish() {}

    public static void main(String[] args) {
        new Lanternfish().solve();
    }

    @Override
    public void solve() {
        List<Fish> fishes = Arrays.stream(getInputLines(","))
                .mapToInt(Integer::parseInt)
                .mapToObj(Fish::new)
                .collect(Collectors.toList());

        for (int day = 0; day < 80; day++) {
            List<Fish> tempFishes = new ArrayList<>(fishes);

            for (Fish fish : fishes) {
                if (fish.isReadyToSpawnNew()) {
                    fish.reset();
                    tempFishes.add(new Fish());
                } else {
                    fish.decrease();
                }
            }

            fishes = tempFishes;
        }

        log.info(String.valueOf(fishes.size()));
    }

}

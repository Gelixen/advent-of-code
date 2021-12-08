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

        long[] newFishTrends = new long[257];

        for (int day = 1; day < 9; day++) {
            List<Fish> tempFishes = new ArrayList<>(fishes);

            for (Fish fish : fishes) {
                if (fish.isReadyToSpawnNew()) {
                    fish.reset();
                    tempFishes.add(new Fish());
                    newFishTrends[day]++;
                } else {
                    fish.decrease();
                }
            }

            fishes = tempFishes;
        }

        long fishCount = fishes.size();

        for (int day = 9; day < 257; day++) {
            newFishTrends[day] = newFishTrends[day - 9] + newFishTrends[day - 7];
            fishCount += newFishTrends[day];
        }

        log.info(String.valueOf(fishCount));
    }

}

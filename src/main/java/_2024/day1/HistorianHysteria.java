package _2024.day1;

import util.SolvableTwoPartsTask;

import java.util.*;

public class HistorianHysteria implements SolvableTwoPartsTask {
    public static void main(String[] args) {
        new HistorianHysteria().solve1Part();
        new HistorianHysteria().solve2Part();
    }

    @Override
    public void solve1Part() {
        Queue<Integer> firstLocationIdsList = new PriorityQueue<>();
        Queue<Integer> secondLocationIdsList = new PriorityQueue<>();

        Arrays.stream(getInputLines()).forEach(line -> {
            String[] locationIds = line.split(" {3}");
            firstLocationIdsList.add(Integer.valueOf(locationIds[0]));
            secondLocationIdsList.add(Integer.valueOf(locationIds[1]));
        });

        int result = 0;
        int size = firstLocationIdsList.size();
        for (int i = 0; i < size; i++) {
            result += Math.abs(firstLocationIdsList.remove() - secondLocationIdsList.remove());
        }

        System.out.println(result);
    }

    @Override
    public void solve2Part() {
        Queue<Integer> firstLocationIdsList = new PriorityQueue<>();
        Map<Integer, Integer> secondLocationIdsList = new HashMap<>();

        Arrays.stream(getInputLines()).forEach(line -> {
            String[] locationIds = line.split(" {3}");
            firstLocationIdsList.add(Integer.valueOf(locationIds[0]));
            secondLocationIdsList.compute(Integer.valueOf(locationIds[1]), (key, value) -> (value == null) ? 1 : value + 1);
        });

        var result = firstLocationIdsList.stream()
                .map(locationId -> locationId * secondLocationIdsList.getOrDefault(locationId, 0))
                .reduce(0, Integer::sum);

        System.out.println(result);
    }
}

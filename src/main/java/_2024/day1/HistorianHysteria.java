package _2024.day1;

import util.SolvableTwoPartsTask;

import java.util.*;

public class HistorianHysteria implements SolvableTwoPartsTask {
    public static void main(String[] args) {
        System.out.println(new HistorianHysteria().solve1Part());
        System.out.println(new HistorianHysteria().solve2Part());
    }

    @Override
    public int solve1Part() {
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

        return result;
    }

    @Override
    public int solve2Part() {
        Queue<Integer> firstLocationIdsList = new PriorityQueue<>();
        Map<Integer, Integer> secondLocationIdsList = new HashMap<>();

        Arrays.stream(getInputLines()).forEach(line -> {
            String[] locationIds = line.split(" {3}");
            firstLocationIdsList.add(Integer.valueOf(locationIds[0]));
            secondLocationIdsList.compute(Integer.valueOf(locationIds[1]), (key, value) -> (value == null) ? 1 : value + 1);
        });

        return firstLocationIdsList.stream()
                .map(locationId -> locationId * secondLocationIdsList.getOrDefault(locationId, 0))
                .reduce(0, Integer::sum);
    }
}

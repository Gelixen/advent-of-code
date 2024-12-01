package _2024.day1;

import lombok.extern.java.Log;
import util.SolvableTask;

import java.util.*;

@Log
public class HistorianHysteria implements SolvableTask {
    public static void main(String[] args) {
        new HistorianHysteria().solve();
    }

    @Override
    public void solve() {
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
}
